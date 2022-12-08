package ru.lachesis.translator.view

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lachesis.translator.R
import ru.lachesis.translator.databinding.MainFragmentBinding
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.view.base.BaseFragment
import ru.lachesis.translator.view.main.MainViewModel
import ru.lachesis.translator.view.main.rvadapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.lachesis.translator.utils.network.isOnline
import ru.lachesis.translator.view.description.DescriptionFragment

class MainFragment: BaseFragment<AppState>() {
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() = _binding!!

    override lateinit var viewModel: MainViewModel

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }


    private val onFabClickListener: View.OnClickListener = View.OnClickListener{
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
        searchDialogFragment.show(childFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)

    }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, DescriptionFragment.newInstance(data),null )?.addToBackStack(null)?.commit()

            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(requireContext())
                if (isNetworkAvailable) {
                    viewModel.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

        const val BUNDLE_EXTRA = "Theme"
        fun newInstance(bundle: Bundle?): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        initViewModel()
        initViews()
        setHasOptionsMenu(true)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(
                object :       SearchDialogFragment.OnSearchClickListener {
                    override fun onClick(searchWord: String) {
//                        viewModel.getData(searchWord, true).observe(requireActivity(), observer)
                        isNetworkAvailable = isOnline(requireContext())
                        if (isNetworkAvailable) {
                            viewModel.getData(searchWord, isNetworkAvailable)
                        } else {
                            showNoInternetConnectionDialog()
                        }
                }
            })
            searchDialogFragment.show(childFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initViewModel() {
        if (binding.mainFragmentRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val vm: MainViewModel by viewModel()
        viewModel =vm
        viewModel.subscribe().observe(requireActivity(), { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(onFabClickListener)
        binding.mainFragmentRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.mainFragmentRecyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}


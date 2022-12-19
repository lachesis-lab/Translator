package ru.lachesis.translator.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.lachesis.translator.R
import ru.lachesis.translator.databinding.HistoryFragmentBinding
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.view.MainActivity
import ru.lachesis.translator.view.base.BaseFragment
import ru.lachesis.translator.view.main.rvadapter.MainAdapter
import ru.lachesis.translator.viewmodel.BaseViewModel

class HistoryFragment(): BaseFragment<AppState>() {

    private var _binding : HistoryFragmentBinding? = null
    private val binding: HistoryFragmentBinding
        get() = _binding!!
    lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        initViewModel()
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.historyActivityRecyclerview.adapter = adapter
    }

    private fun initViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val vm: HistoryViewModel by viewModel()

        model =vm
        model.subscribe().observe(requireActivity() as MainActivity, { renderData(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }


    override val viewModel: BaseViewModel<AppState>
        get() = TODO("Not yet implemented")

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }


}
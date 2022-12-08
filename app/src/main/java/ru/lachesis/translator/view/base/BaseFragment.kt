package ru.lachesis.translator.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import ru.lachesis.translator.R
import ru.lachesis.translator.databinding.LoadingLayoutBinding
import ru.lachesis.translator.databinding.MainFragmentBinding
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.utils.AlertDialogFragment
import ru.lachesis.translator.utils.network.isOnline
import ru.lachesis.translator.view.history.HistoryFragment
import ru.lachesis.translator.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val viewModel: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false
    private lateinit var  binding: LoadingLayoutBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNetworkAvailable = isOnline(requireContext())
        binding = LoadingLayoutBinding.inflate(layoutInflater)

    }

    override fun onResume() {
        super.onResume()

        binding = LoadingLayoutBinding.inflate(layoutInflater)
        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }

/*
//        /*
//           binding = LoadingLayoutBinding.inflate(layoutInflater)
//           isNetworkAvailable = isOnline(requireContext())
//           if (!isNetworkAvailable && isDialogNull()) {
//               showNoInternetConnectionDialog()
//           }
showNoInternetConnectionDialog*/
//   */
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(childFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return childFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_title_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.history_menu_item)   {
            childFragmentManager.beginTransaction().add(R.id.history_frame, HistoryFragment.newInstance(),"").commit()
            true}
        else super.onOptionsItemSelected(item)
    }
*/

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding = LoadingLayoutBinding.inflate(layoutInflater)

        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    abstract fun setDataToAdapter(data: List<DataModel>)

}


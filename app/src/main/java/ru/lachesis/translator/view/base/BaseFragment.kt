package ru.lachesis.translator.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.lachesis.translator.R
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.utils.AlertDialogFragment
import ru.lachesis.translator.utils.network.isOnline
import ru.lachesis.translator.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val viewModel: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNetworkAvailable = isOnline(requireContext())
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
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

    abstract  fun renderData(appState: AppState)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }

}


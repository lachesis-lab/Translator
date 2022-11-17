package ru.lachesis.translator.view.base

import androidx.fragment.app.Fragment
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val viewModel: BaseViewModel<T>

    abstract  fun renderData(appState: AppState)


}


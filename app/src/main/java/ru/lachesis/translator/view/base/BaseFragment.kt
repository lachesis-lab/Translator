package ru.lachesis.translator.view.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.presenter.Presenter

abstract class BaseFragment<T:AppState>: Fragment(),MvpView {

    protected lateinit var presenter: Presenter<T, MvpView>

    protected abstract fun createPresenter(): Presenter<T, MvpView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach(this)
    }

}
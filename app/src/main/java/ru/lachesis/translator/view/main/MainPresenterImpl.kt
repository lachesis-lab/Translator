package ru.lachesis.translator.view.main

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.datasource.DataSourceLocal
import ru.lachesis.translator.model.datasource.DataSourceRemote
import ru.lachesis.translator.model.repository.RepositorySimpleImpl
import ru.lachesis.translator.presenter.Presenter
import ru.lachesis.translator.rx.SchedulerProvider
import ru.lachesis.translator.view.base.MvpView

class MainPresenterImpl<T : AppState, V : MvpView>(
    private val interactor:
    MainInteractor = MainInteractor(
        RepositorySimpleImpl(DataSourceRemote()),
        RepositorySimpleImpl(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {
    private var currentView: V? = null

    override fun attach(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detach(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {


            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }


            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onComplete() {
            }
        }
    }
}
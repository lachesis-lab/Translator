package ru.lachesis.translator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.datasource.DataSourceLocal
import ru.lachesis.translator.model.datasource.DataSourceRemote
import ru.lachesis.translator.model.repository.RepositorySimpleImpl
import ru.lachesis.translator.viewmodel.BaseViewModel

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositorySimpleImpl(DataSourceRemote()),
        RepositorySimpleImpl(DataSourceLocal())
    )
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<in AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                appState = t
                liveDataToObserve.value = t
            }

            override fun onError(e: Throwable) {
                liveDataToObserve.value= AppState.Error(e)
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }
        }

    }
}
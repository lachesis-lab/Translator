package ru.lachesis.translator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val liveDataToObserve: MutableLiveData<T> = MutableLiveData<T>(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataToObserve
}


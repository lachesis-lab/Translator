package ru.lachesis.translator.presenter

import io.reactivex.Observable
import io.reactivex.Single
import ru.lachesis.translator.model.data.AppState

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState>
}
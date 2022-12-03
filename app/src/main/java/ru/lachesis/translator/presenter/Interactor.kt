package ru.lachesis.translator.presenter

import ru.lachesis.translator.model.data.AppState

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): AppState
}
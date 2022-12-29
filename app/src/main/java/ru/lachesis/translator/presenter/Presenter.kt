package ru.lachesis.translator.presenter

import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.view.base.MvpView

interface Presenter<T: AppState, V: MvpView> {
    fun attach(view:V)

    fun detach(view: V)

    fun getData(word: String, isOnline: Boolean)
}
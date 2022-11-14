package ru.lachesis.translator.view.base

import ru.lachesis.translator.model.data.AppState

interface MvpView {
fun renderData(appState: AppState)
}
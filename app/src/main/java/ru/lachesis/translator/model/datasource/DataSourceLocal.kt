package ru.lachesis.translator.model.datasource

import ru.lachesis.translator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}

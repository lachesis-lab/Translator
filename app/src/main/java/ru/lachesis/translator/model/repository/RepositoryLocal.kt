package ru.lachesis.translator.model.repository

import ru.lachesis.translator.model.data.AppState

interface RepositoryLocal<T>: Repository<T> {
    suspend fun saveToDB(appState: AppState)
}
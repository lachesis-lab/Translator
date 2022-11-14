package ru.lachesis.translator.view.main

import io.reactivex.Observable
import io.reactivex.Single
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.repository.Repository
import ru.lachesis.translator.presenter.Interactor

class MainInteractor(
    private val localRepository: Repository<List<DataModel>>,
    private val remoteRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Single<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}


package ru.lachesis.translator.view.main

import io.reactivex.Single
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.repository.Repository
import ru.lachesis.translator.presenter.Interactor

class MainInteractor(
    private val localRepository:Repository<List<DataModel>>,
    private val remoteRepository:Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Single<AppState> {
       return  localRepository.getData("test").map{AppState.Success(it)}
    }
}


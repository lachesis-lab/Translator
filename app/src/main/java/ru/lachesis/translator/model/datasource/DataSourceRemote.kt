package ru.lachesis.translator.model.datasource

import io.reactivex.Single
import ru.lachesis.translator.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()): DataSource<List<DataModel>> {
    override fun getData(word: String): Single<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}
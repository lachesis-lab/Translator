package ru.lachesis.translator.model.datasource

import io.reactivex.Single
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.data.Meanings

class DataSourceLocal(private val localProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()): DataSource<List<DataModel>> {
    override fun getData(word: String): Single<List<DataModel>> {
        return localProvider.getData(word)
    }
}
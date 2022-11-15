package ru.lachesis.translator.model.datasource

import io.reactivex.Observable
import ru.lachesis.translator.model.data.DataModel

class DataSourceLocal(private val localProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()): DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return localProvider.getData(word)
    }
}
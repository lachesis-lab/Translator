package ru.lachesis.translator.model.datasource

import io.reactivex.Observable
import ru.lachesis.translator.model.data.DataModel

class RoomDataBaseImplementation(): DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }

}

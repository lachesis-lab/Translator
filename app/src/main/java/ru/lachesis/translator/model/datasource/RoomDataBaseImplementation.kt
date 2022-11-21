package ru.lachesis.translator.model.datasource

import io.reactivex.Observable
import ru.lachesis.translator.model.data.DataModel

class RoomDataBaseImplementation(): DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

}

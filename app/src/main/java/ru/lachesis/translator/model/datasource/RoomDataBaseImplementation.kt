package ru.lachesis.translator.model.datasource

import io.reactivex.Single
import ru.lachesis.translator.model.data.DataModel

class RoomDataBaseImplementation(): DataSource<List<DataModel>> {
    override fun getData(word: String): Single<List<DataModel>> {
        TODO("Not yet implemented")
    }

}

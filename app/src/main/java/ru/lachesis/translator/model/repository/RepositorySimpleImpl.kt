package ru.lachesis.translator.model.repository

import io.reactivex.Observable
import ru.lachesis.translator.model.data.DataModel

class RepositorySimpleImpl(private val dataSource: ru.lachesis.translator.model.datasource.DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
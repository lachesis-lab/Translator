package ru.lachesis.translator.model.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.data.Meanings
import javax.sql.DataSource

class RepositorySimpleImpl(private val dataSource: ru.lachesis.translator.model.datasource.DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Single<List<DataModel>> {
        return dataSource.getData(word)
    }
}
package ru.lachesis.translator.model.repository

import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.datasource.DataSource

class RepositorySimpleImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
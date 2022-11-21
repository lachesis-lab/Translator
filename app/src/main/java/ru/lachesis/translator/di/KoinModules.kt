package ru.lachesis.translator.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.datasource.RetrofitImplementation
import ru.lachesis.translator.model.datasource.RoomDataBaseImplementation
import ru.lachesis.translator.model.repository.Repository
import ru.lachesis.translator.model.repository.RepositorySimpleImpl
import ru.lachesis.translator.view.main.MainInteractor
import ru.lachesis.translator.view.main.MainViewModel


    val application = module {
        single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
            RepositorySimpleImpl(
                RetrofitImplementation()
            )
        }
        single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
            RepositorySimpleImpl(
                RoomDataBaseImplementation()
            )
        }
    }
    val mainScreen = module {
        factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
        factory { MainViewModel(get()) }
    }

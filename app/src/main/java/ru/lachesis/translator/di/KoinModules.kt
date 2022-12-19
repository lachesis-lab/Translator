package ru.lachesis.translator.di

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.model.datasource.RetrofitImplementation
import ru.lachesis.translator.model.datasource.RoomDataBaseImplementation
import ru.lachesis.translator.model.repository.Repository
import ru.lachesis.translator.model.repository.RepositoryImplementationLocal
import ru.lachesis.translator.model.repository.RepositoryLocal
import ru.lachesis.translator.model.repository.RepositorySimpleImpl
import ru.lachesis.translator.room.HistoryDB
import ru.lachesis.translator.view.history.HistoryInteractor
import ru.lachesis.translator.view.history.HistoryViewModel
import ru.lachesis.translator.view.main.MainInteractor
import ru.lachesis.translator.view.main.MainViewModel


val application = module {
    single { Room.databaseBuilder(get(), HistoryDB::class.java, "HistoryDB").build() }
    single { get<HistoryDB>().historyDao() }
    single<Repository<List<DataModel>>> { RepositorySimpleImpl(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

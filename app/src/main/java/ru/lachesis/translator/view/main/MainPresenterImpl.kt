package ru.lachesis.translator.view.main

import io.reactivex.disposables.CompositeDisposable
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.model.datasource.DataSourceLocal
import ru.lachesis.translator.model.datasource.DataSourceRemote
import ru.lachesis.translator.model.repository.RepositorySimpleImpl
import ru.lachesis.translator.presenter.Presenter
import ru.lachesis.translator.rx.SchedulerProvider
import ru.lachesis.translator.view.base.MvpView
class MainPresenterImpl<T:AppState,V:MvpView>(
    private  val interactor:
        MainInteractor = MainInteractor(
        RepositorySimpleImpl(DataSourceRemote()),
        RepositorySimpleImpl(DataSourceLocal())),
        protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
):Presenter<T,V>{
    override fun attach(view: V) {
        interactor
    }

    override fun detach(view: V) {
        TODO("Not yet implemented")
    }

    override fun getData(word: String, isOnline: Boolean) {
        TODO("Not yet implemented")
    }
}
//class MainPresenterImpl<T:AppState,V:MvpView>(
//    private val interactor :MainInteractor = MainInteractor(RepositorySimpleImpl(),RepositorySimpleImpl()),
//    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
//  //  protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
//): Presenter<T:AppState,V:MvpView> {
//    private var currentView: V? = null

//}
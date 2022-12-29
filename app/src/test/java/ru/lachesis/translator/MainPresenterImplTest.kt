package ru.lachesis.translator

import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.presenter.Presenter
import ru.lachesis.translator.rx.SchedulerProvider
import ru.lachesis.translator.view.base.MvpView
import ru.lachesis.translator.view.main.MainInteractor
import ru.lachesis.translator.view.main.MainPresenterImpl

class MainPresenterImplTest {

    private lateinit var presenter: MainPresenterImpl<AppState,MvpView>

    @Mock
    private lateinit var interactor: MainInteractor

    @Mock
    private lateinit var mvpView: MvpView

    @Mock
    protected lateinit var compositeDisposable: CompositeDisposable

    @Mock
    protected lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenterImpl(interactor,compositeDisposable,schedulerProvider,mvpView)
        presenter.

    }

    @Test
    fun getData_Test() {
        val searchQuery = "some query"
        presenter.getData("some query",true)
        Mockito.verify(interactor, Mockito.times(1)).getData(searchQuery,true)
    }



    @Test //Проверяем работу метода handleGitHubError()
    fun emptySearch_Test() {
        //Вызываем у Презентера метод handleGitHubError()
        presenter.getData("",true)
        //Проверяем, что у viewContract вызывается метод displayError()
        Mockito.verify(interactor, Mockito.times(1))
    }

}
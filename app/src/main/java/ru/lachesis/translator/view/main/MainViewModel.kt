package ru.lachesis.translator.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.utils.parseOnlineSearchResults
import ru.lachesis.translator.utils.parseSearchResults
import ru.lachesis.translator.viewmodel.BaseViewModel

class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = liveDataToObserve
 //   private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = AppState.Loading(null)
       cancelJob()
       viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        liveDataToObserve.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
    }

    override fun onCleared() {
        liveDataToObserve.value = AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}
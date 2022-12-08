package ru.lachesis.translator.view.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.lachesis.translator.model.data.AppState
import ru.lachesis.translator.utils.parseLocalSearchResults
import ru.lachesis.translator.utils.parseSearchResults
import ru.lachesis.translator.viewmodel.BaseViewModel

class HistoryViewModel(val interactor: HistoryInteractor): BaseViewModel<AppState>() {

    private val historyLiveData : LiveData<AppState> = liveDataToObserve

    override fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

/*
    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        liveDataToObserve.postValue(parseSearchResults(interactor.getData(word, isOnline)))
    }
*/
private suspend fun startInteractor(word: String, isOnline: Boolean) {
    liveDataToObserve.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
}

    override fun onCleared() {
        liveDataToObserve.value = AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }

    fun subscribe(): LiveData<AppState>{
        return historyLiveData
    }

}
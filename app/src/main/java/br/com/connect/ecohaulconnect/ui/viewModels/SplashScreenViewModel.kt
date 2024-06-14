package br.com.connect.ecohaulconnect.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.connect.ecohaulconnect.preferences.PreferencesKey.LOGGEDIN
import br.com.connect.ecohaulconnect.preferences.datastore
import br.com.connect.ecohaulconnect.ui.state.AppState
import br.com.connect.ecohaulconnect.ui.state.SplashScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class SplashScreenViewModel(application: Application): AndroidViewModel(application) {
    private val datastore = getApplication<Application>().applicationContext.datastore
    private val _uiState: MutableStateFlow<SplashScreenUiState> = MutableStateFlow(SplashScreenUiState())
    val uiState get() = _uiState

    init {
       viewModelScope.launch {
           setInitialDestination()
       }
    }

    private suspend fun setInitialDestination() {
        datastore.data.collect {
            val appState = if (it[LOGGEDIN] == true) AppState.LoggedIn else AppState.LoggedOut
            _uiState.value = _uiState.value.copy(
                appState = appState
            )
        }
    }

}
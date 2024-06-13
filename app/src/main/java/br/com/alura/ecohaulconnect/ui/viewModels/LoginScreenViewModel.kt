package br.com.alura.ecohaulconnect.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.ID
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.LOGGEDIN
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.repositories.EcoHaulRepository
import br.com.alura.ecohaulconnect.ui.state.LoginScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(LoginScreenUiState())
    private val datastore = getApplication<Application>().applicationContext.datastore
    private val repository = EcoHaulRepository.getInstance(datastore)
    val uiState get() = _uiState

    init {
        _uiState.update { state ->
            state.copy(
                onUserChange = {
                    _uiState.value = _uiState.value.copy(
                        user = it
                    )
                },
                onPasswordChange = {
                    _uiState.value = _uiState.value.copy(
                        password = it
                    )
                },
                onError = {
                    _uiState.value = _uiState.value.copy(
                        showError = it
                    )
                }
            )
        }
    }

    fun login() {
        viewModelScope.launch {
            tryLogin()
            datastore.data.collect {
                val isLoggedIn = it[LOGGEDIN]
                val id = it[ID]
                Log.i("LoginScreenViewModel", "loginStore: $isLoggedIn")
                Log.i("LoginScreenViewModel", "id: $id")
                if (isLoggedIn == true) {
                    _uiState.value = _uiState.value.copy (
                        isLoggedIn = true
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        showError = true
                    )
                }
            }
        }
    }

    private suspend fun tryLogin() {
        repository.executeLogin(_uiState.value.user, _uiState.value.password)
    }

//    private suspend fun tryGetUserId() {
//        Log.i("LoginScreenViewModel", "tryGetUserId: called method")
//        repository.getClientId(_uiState.value.user)
//    }
}
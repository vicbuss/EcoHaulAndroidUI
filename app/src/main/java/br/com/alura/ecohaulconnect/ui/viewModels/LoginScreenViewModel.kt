package br.com.alura.ecohaulconnect.ui.viewModels

import androidx.lifecycle.ViewModel
import br.com.alura.ecohaulconnect.ui.state.LoginScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<LoginScreenUiState> = MutableStateFlow(LoginScreenUiState())
    val uiState get() = _uiState

    init {
        _uiState.update { state ->
            state.copy (
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
}
package br.com.alura.ecohaulconnect.ui.viewModels

import androidx.lifecycle.ViewModel
import br.com.alura.ecohaulconnect.ui.state.SignupFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SignupScreenViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<SignupFormScreenUiState> = MutableStateFlow(
        SignupFormScreenUiState()
    )
    val uiState get() = _uiState

    init {
        _uiState.update { state ->
            state.copy(
                onPasswordChange = {
                    _uiState.value = _uiState.value.copy(
                        password = it
                    )
                },
                onLoginChange = {
                    _uiState.value = _uiState.value.copy(
                        login = it
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onCpfChange = {
                    _uiState.value = _uiState.value.copy(
                        cpf = it
                    )
                },
                onZipCodeChange = {
                    _uiState.value = _uiState.value.copy(
                        zipCode = it
                    )
                },
                onComplementChange = {
                    _uiState.value = _uiState.value.copy(
                        complement = it
                    )
                },
                onNeighborhoodChange = {
                    _uiState.value = _uiState.value.copy(
                        neighborhood = it
                    )
                },
                onNumberChange = {
                    _uiState.value = _uiState.value.copy(
                        number = it
                    )
                },
                onCityChange = {
                    _uiState.value = _uiState.value.copy(
                        city = it
                    )
                },
                onStreetChange = {
                    _uiState.value = _uiState.value.copy(
                        street = it
                    )
                },
                onBirthDateChange = {
                    _uiState.value = _uiState.value.copy(
                        birthDate = it
                    )
                },
                onFederalStateChange = {
                    _uiState.value = _uiState.value.copy(
                        federalState = it
                    )
                },
                onPhoneNumberChange = {
                    _uiState.value = _uiState.value.copy(
                        phoneNumber = it
                    )
                },
                onOpenDateDialog = {
                    _uiState.value = _uiState.value.copy(
                        openDateDialog = true
                    )
                },
                onCloseDateDialog = {
                    _uiState.value = _uiState.value.copy(
                        openDateDialog = false
                    )
                }
            )
        }
    }
}
package br.com.alura.ecohaulconnect.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.extensions.fromBrazilianDateToLocalDate
import br.com.alura.ecohaulconnect.extensions.toUTCDate
import br.com.alura.ecohaulconnect.network.dtos.AddressData
import br.com.alura.ecohaulconnect.network.dtos.LoginBody
import br.com.alura.ecohaulconnect.network.dtos.UserData
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.repositories.EcoHaulRepository
import br.com.alura.ecohaulconnect.ui.state.SignupFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupScreenViewModel(application: Application): AndroidViewModel(application) {
    private val _uiState: MutableStateFlow<SignupFormScreenUiState> = MutableStateFlow(
        SignupFormScreenUiState()
    )
    private val dataStore = getApplication<Application>().applicationContext.datastore
    private val repository = EcoHaulRepository.getInstance(dataStore)
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

    fun signup() {
        _uiState.value.run {
            val loginData = LoginBody(
                login = login,
                senha = password
            )

            val addressData = AddressData(
                complemento = complement,
                numero = number,
                uf = federalState,
                cidade = city,
                cep = zipCode,
                bairro = neighborhood,
                logradouro = street
            )

            val userData = UserData(
                endereco = addressData,
                cpf = cpf,
                telefone = phoneNumber,
                nome = name,
                email = login,
                dataNascimento = birthDate.fromBrazilianDateToLocalDate().toUTCDate()
            )
            viewModelScope.launch {
                repository.signup(loginData = loginData, userData = userData)
            }
        }

    }
}
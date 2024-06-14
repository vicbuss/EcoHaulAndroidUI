package br.com.connect.ecohaulconnect.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.connect.ecohaulconnect.model.Service
import br.com.connect.ecohaulconnect.network.dtos.toService
import br.com.connect.ecohaulconnect.preferences.datastore
import br.com.connect.ecohaulconnect.repositories.EcoHaulRepository
import br.com.connect.ecohaulconnect.ui.state.ServiceListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceListScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState: MutableStateFlow<ServiceListUiState> =
        MutableStateFlow(ServiceListUiState())
    private val dataStore = getApplication<Application>().applicationContext.datastore
    private val repository = EcoHaulRepository.getInstance(dataStore)

    val uiState get() = _uiState

    init {
        viewModelScope.launch {
            val services = listServices()
            _uiState.value = _uiState.value.copy(
                services = services
            )
        }
    }

    private suspend fun listServices(): List<Service> {
        val serviceResponse = repository.listServices()
        return serviceResponse.map { serviceData ->  serviceData.toService()}
    }
}
package br.com.alura.ecohaulconnect.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.data.ServiceDao
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.network.dtos.toService
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.repositories.EcoHaulRepository
import br.com.alura.ecohaulconnect.ui.state.ServiceListUiState
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
        val serviceResponse = repository.getServiceList()
        return serviceResponse.map { serviceData ->  serviceData.toService()}
    }
}
package br.com.alura.ecohaulconnect.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.network.dtos.toService
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.repositories.EcoHaulRepository
import br.com.alura.ecohaulconnect.ui.state.ServiceDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceDetailsScreenViewModel(private val serviceId: Long, application: Application): AndroidViewModel(application) {
    private val _uiState: MutableStateFlow<ServiceDetailsUiState> = MutableStateFlow(
        ServiceDetailsUiState()
    )
    private val dataStore = getApplication<Application>().applicationContext.datastore
    private val repository = EcoHaulRepository.getInstance(dataStore)

    val uiState get() = _uiState

    init {
        viewModelScope.launch {
            loadService()
        }
    }

    private suspend fun loadService() {
        val serviceData = repository.detailService(serviceId)
        serviceData?.let {
            val service = it.toService()
            _uiState.value = _uiState.value.copy(
                service = service,
                topBarTitle = service.description
            )
        }
    }

    fun removeService(){
        // dao.removeService(serviceId)
    }
}
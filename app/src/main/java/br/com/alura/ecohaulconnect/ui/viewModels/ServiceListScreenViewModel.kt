package br.com.alura.ecohaulconnect.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.data.ServiceDao
import br.com.alura.ecohaulconnect.ui.state.ServiceListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceListScreenViewModel: ViewModel() {
    private val dao = ServiceDao()
    private val _uiState: MutableStateFlow<ServiceListUiState> = MutableStateFlow(ServiceListUiState())

    val uiState get() = _uiState

    init {
        viewModelScope.launch {
            val services = dao.listServices()
            services.collect {
                _uiState.value = _uiState.value.copy(
                   services = it
                )
            }
        }
    }
}
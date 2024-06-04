package br.com.alura.ecohaulconnect.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.ecohaulconnect.data.ServiceDao
import br.com.alura.ecohaulconnect.ui.state.ServiceDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceDetailsScreenViewModel(private val serviceId: Long): ViewModel() {
    private val dao = ServiceDao()
    private val _uiState: MutableStateFlow<ServiceDetailsUiState> = MutableStateFlow(
        ServiceDetailsUiState()
    )

    val uiState get() = _uiState

    init {
        viewModelScope.launch {
            loadService()
        }
    }

    private fun loadService() {
        val service = dao.getServiceById(serviceId)
        service?.let {
            _uiState.value = _uiState.value.copy(
                service = it,
                topBarTitle = it.description
            )
            Log.i("ServiceDetails", "loadService: $service")
        }
    }

    fun removeService(){
        dao.removeService(serviceId)
    }
}
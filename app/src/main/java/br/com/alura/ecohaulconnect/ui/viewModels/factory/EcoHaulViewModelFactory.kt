package br.com.alura.ecohaulconnect.ui.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceDetailsScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceFormScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceListScreenViewModel

class EcoHaulViewModelFactory(private val serviceId: Long = 0L) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceFormScreenViewModel::class.java)) {
            return ServiceFormScreenViewModel(serviceId) as T
        } else if (modelClass.isAssignableFrom(ServiceListScreenViewModel::class.java)) {
            return ServiceListScreenViewModel() as T
        } else if (modelClass.isAssignableFrom(ServiceDetailsScreenViewModel::class.java)) {
            return ServiceDetailsScreenViewModel(serviceId) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}
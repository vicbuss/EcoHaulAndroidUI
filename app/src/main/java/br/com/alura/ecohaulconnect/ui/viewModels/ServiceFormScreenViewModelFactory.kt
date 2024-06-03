package br.com.alura.ecohaulconnect.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ServiceFormScreenViewModelFactory(private val serviceId: Long): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ServiceFormScreenViewModel::class.java)) {
            return ServiceFormScreenViewModel(serviceId) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}
package br.com.connect.ecohaulconnect.ui.viewModels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.connect.ecohaulconnect.ui.viewModels.LoginScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.ServiceDetailsScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.ServiceFormScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.ServiceListScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.SignupScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.SplashScreenViewModel

class EcoHaulViewModelFactory(
    private val serviceId: Long = 0L,
    private val application: Application? = null
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceFormScreenViewModel::class.java)) {
            if(application !== null) {
                return ServiceFormScreenViewModel(serviceId, application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for ServiceFormScreenViewModel")
            }
        } else if (modelClass.isAssignableFrom(ServiceListScreenViewModel::class.java)) {
            if (application !== null) {
                return ServiceListScreenViewModel(application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for ServiceScreenViewModel")
            }
        } else if (modelClass.isAssignableFrom(ServiceDetailsScreenViewModel::class.java)) {
            if (application !== null) {
                return ServiceDetailsScreenViewModel(serviceId = serviceId, application = application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for ServiceDetailsScreenViewModel")
            }
        } else if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            if (application !== null) {
                return LoginScreenViewModel(application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for LoginScreenViewModel")
            }
        } else if (modelClass.isAssignableFrom(SignupScreenViewModel::class.java)) {
            if (application !== null) {
                return SignupScreenViewModel(application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for SignupScreenViewModel")
            }
        } else if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            if (application !== null) {
                return SplashScreenViewModel(application) as T
            } else {
                throw IllegalArgumentException("Application must be provided for SplashScreenViewModel")
            }
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}
package br.com.alura.ecohaulconnect.data

import br.com.alura.ecohaulconnect.model.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class ServiceDao {
    companion object {
        private val serviceList = MutableStateFlow<List<Service>>(emptyList())
    }
    fun listServices() = serviceList.asStateFlow()

    fun getServiceById(id: Long): Service? {
        return serviceList.value.find { service: Service -> service.id == id  }
    }

    fun editService(service: Service): Service {
        serviceList.update { currentList ->
            currentList.map { if(it.id == service.id) service else it }
        }

        return service
    }

    fun addService(service: Service): Service {
        with(service) {
            val savedService = Service(
                id = Random.nextLong(),
                description = description,
                status = status,
                value = value,
                date = date,
                address = address,
                items = items,
                category = category
            )

            serviceList.value = serviceList.value + savedService
            return savedService
        }
    }

    fun removeService(service: Service) {
        serviceList.update { currentList ->
            currentList.filterNot { it.id == service.id }
        }
    }
}
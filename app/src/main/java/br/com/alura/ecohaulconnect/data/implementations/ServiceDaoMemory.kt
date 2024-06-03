package br.com.alura.ecohaulconnect.data.implementations

import br.com.alura.ecohaulconnect.data.contracts.IServiceDao
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList

class ServiceDaoMemory: IServiceDao {
    companion object {
        private val serviceList: MutableList<Service> = mutableListOf()
    }
    override fun listServices(): List<Service> {
        return serviceList.toList()
    }

    override fun getServiceById(id: Long): Service? {
        return serviceList.find { service: Service -> service.id == id  }
    }

    override fun editService(service: Service): Service? {
        val serviceIndex = serviceList.indexOfFirst { it.id ==  service.id }
        if (serviceIndex > 0) {
            serviceList[serviceIndex] = service
            return service
        }
        return null
    }

    override fun addService(service: Service): Service {
        serviceList.add(service)
        return service
    }

    override fun removeService(service: Service) {
        val serviceIndex = serviceList.indexOfFirst { it.id ==  service.id }
        if (serviceIndex > 0) {
            serviceList.removeAt(serviceIndex)
        }
    }
}
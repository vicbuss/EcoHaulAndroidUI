package br.com.alura.ecohaulconnect.data.implementations

import br.com.alura.ecohaulconnect.data.contracts.IServiceDao
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList

class ServiceDaoMemory: IServiceDao {
    override fun listServices(): List<Service> {
        return serviceList.toList()
    }

    override fun editService(service: Service): Service {
        val serviceIndex = serviceList.indexOfFirst { it.id ==  service.id }
        serviceList[serviceIndex] = service

        return service
    }

    override fun addService(service: Service): Service {
        serviceList.add(service)
        return service
    }

    override fun removeService(service: Service) {
        val serviceIndex = serviceList.indexOfFirst { it.id ==  service.id }
        serviceList.removeAt(serviceIndex)
    }

    companion object {
        private val serviceList: MutableList<Service> = sampleServiceList.sortedByDescending { service -> service.date }.toMutableList()
    }
}
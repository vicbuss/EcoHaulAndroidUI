package br.com.alura.ecohaulconnect.data.contracts

import br.com.alura.ecohaulconnect.model.Service

interface IServiceDao {
    fun listServices(): List<Service>
    fun getServiceById(id: Long): Service?
    fun editService(service: Service): Service?
    fun addService(service: Service): Service
    fun removeService(service: Service)
}
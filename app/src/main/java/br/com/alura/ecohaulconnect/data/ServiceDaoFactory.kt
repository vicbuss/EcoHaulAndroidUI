package br.com.alura.ecohaulconnect.data

import br.com.alura.ecohaulconnect.data.contracts.IServiceDao
import br.com.alura.ecohaulconnect.data.implementations.ServiceDaoApi
import br.com.alura.ecohaulconnect.data.implementations.ServiceDaoMemory

sealed class RepositoryTypes(val type: String) {
    object Memory: RepositoryTypes("memory")
    object Api: RepositoryTypes("api")
}

class ServiceDaoFactory {
    companion object {
        fun getDao(type: String): IServiceDao {
            val serviceDao = when (type) {
                RepositoryTypes.Memory.type ->  ServiceDaoMemory()
                RepositoryTypes.Api.type -> ServiceDaoApi()
                else -> throw IllegalArgumentException("Repository type is wrong: should be ${RepositoryTypes.Api.type} or ${RepositoryTypes.Memory.type}")
            }
            return serviceDao
        }
    }
}
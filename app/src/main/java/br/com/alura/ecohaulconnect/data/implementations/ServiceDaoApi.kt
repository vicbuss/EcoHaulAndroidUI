package br.com.alura.ecohaulconnect.data.implementations

import br.com.alura.ecohaulconnect.data.contracts.IServiceDao
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.solutions.ApiClient
import retrofit2.Call
import java.math.BigInteger
import retrofit2.Callback
import retrofit2.Response

class ServiceDaoApi : IServiceDao {
    // TODO pegar id
    private val userId: BigInteger = BigInteger("1")
    override fun listServices(): List<Service> {
        var userServiceList: List<Service> = emptyList()
        val call = ApiClient.apiService.getUserServices(this.userId)
        call.enqueue(object : Callback<List<Service>> {
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        userServiceList = it
                    }
                } else {
                    // TODO handle error
                }
            }

            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }
        )
        return userServiceList
    }

    override fun editService(service: Service): Service {
        val call = ApiClient.apiService.editUserService(service.id!!, service)
        var updatedService = service

        call.enqueue(object : Callback<Service> {
            override fun onResponse(call: Call<Service>, response: Response<Service>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        updatedService = it
                    }
                } else {
                    TODO("Handle error")
                }
            }

            override fun onFailure(call: Call<Service>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return updatedService
    }

    override fun addService(service: Service): Service {
        val call = ApiClient.apiService.postUserService(service)
        var returnService = service
        call.enqueue(object : Callback<Service> {
            override fun onResponse(call: Call<Service>, response: Response<Service>) {
                if(response.isSuccessful) {
                   response.body()?.let {
                       returnService = it
                   }
                } else {
                    TODO("Handle error")
                }
            }
            override fun onFailure(call: Call<Service>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return returnService
    }

    override fun removeService(service: Service) {
        val call = ApiClient.apiService.cancelUserService(service.id)
        call.enqueue(object : Callback<Service> {
            override fun onResponse(call: Call<Service>, response: Response<Service>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                    }
                } else {
                    TODO("Handle error")
                }
            }
            override fun onFailure(call: Call<Service>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
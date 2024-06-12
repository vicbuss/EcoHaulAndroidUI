package br.com.alura.ecohaulconnect.network.services

import br.com.alura.ecohaulconnect.network.dtos.LoginBody
import br.com.alura.ecohaulconnect.network.dtos.LoginResponse
import br.com.alura.ecohaulconnect.network.dtos.ServiceData
import br.com.alura.ecohaulconnect.network.dtos.ServiceListResponse
import br.com.alura.ecohaulconnect.network.dtos.UserIdBody
import br.com.alura.ecohaulconnect.network.dtos.UserIdResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun getLoginToken(@Body body: LoginBody): Response<LoginResponse>

    @POST("clientes/email")
    suspend fun getUserId(
        @Body body: UserIdBody,
        @Header("Authorization") token: String
    ): Response<UserIdResponse>

    @GET("servicos")
    suspend fun getUserServices(
        @Query("idCliente") userId: Long,
        @Header("Authorization") token: String
    ): Response<ServiceListResponse>

    @GET("servicos/{idServico}")
    suspend fun detailService(
        @Path("idServico") serviceId: Long,
        @Header("Authorization") token: String
    ): Response<ServiceData>

}
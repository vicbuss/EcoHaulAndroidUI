package br.com.connect.ecohaulconnect.network.services

import br.com.connect.ecohaulconnect.network.dtos.LoginBody
import br.com.connect.ecohaulconnect.network.dtos.LoginResponse
import br.com.connect.ecohaulconnect.network.dtos.NewServiceData
import br.com.connect.ecohaulconnect.network.dtos.ServiceData
import br.com.connect.ecohaulconnect.network.dtos.ServiceListResponse
import br.com.connect.ecohaulconnect.network.dtos.UserData
import br.com.connect.ecohaulconnect.network.dtos.UserIdBody
import br.com.connect.ecohaulconnect.network.dtos.UserIdResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @POST("servicos")
    suspend fun createService(
        @Body body: NewServiceData,
        @Header("Authorization") token: String
    ): Response<ServiceData>

    @PUT("servicos/{idServico}")
    suspend fun updateService(
        @Body body: ServiceData,
        @Path("idServico") serviceId: Long,
        @Header("Authorization") token: String
    ): Response<ServiceData>

    @PATCH("servicos/remover/{idServico}")
    suspend fun cancelService(
        @Path("idServico") serviceId: Long,
        @Header("Authorization") token: String
    ): Response<Unit>

    @POST("clientes")
    suspend fun signupClientData(
        @Body body: UserData
    ): Response<UserData>

    @POST("auth/signup")
    suspend fun signupUserData(
        @Body body: LoginBody
    ): Response<Unit>
}
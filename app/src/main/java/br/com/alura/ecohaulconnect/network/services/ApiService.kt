package br.com.alura.ecohaulconnect.network.services

import br.com.alura.ecohaulconnect.network.dtos.LoginBody
import br.com.alura.ecohaulconnect.network.dtos.LoginResponse
import br.com.alura.ecohaulconnect.network.dtos.UserIdBody
import br.com.alura.ecohaulconnect.network.dtos.UserIdResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun getLoginToken(@Body body: LoginBody): Response<LoginResponse>

    @POST("clientes/email")
    suspend fun getUserId(@Body body: UserIdBody, @Header("Authorization") token: String): Response<UserIdResponse>

//    @GET("servicos")
//    fun getUserServices(@Query("idCliente") userId: BigInteger): Call<List<Service>>
//
//    @POST("servicos")
//    fun postUserService(@Body body: Service): Call<Service>
//
//    @PUT("servicos/{idServico}")
//    fun editUserService(
//        @Path("idServico") serviceId: Long,
//        @Body body: Service
//    ): Call<Service>
//
//    @PATCH("servicos/cancelar/{idServico}")
//    fun cancelUserService(@Path("idServico") serviceId: Long): Call<Service>
}

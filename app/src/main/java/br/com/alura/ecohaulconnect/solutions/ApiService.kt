package br.com.alura.ecohaulconnect.solutions

import br.com.alura.ecohaulconnect.model.Service
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger

interface ApiService {
    @GET("servicos")
    fun getUserServices(@Query("idCliente") userId: BigInteger): Call<List<Service>>

    @POST("servicos")
    fun postUserService(@Body body: Service): Call<Service>

    @PUT("servicos/{idServico}")
    fun editUserService(
        @Path("idServico") serviceId: Long,
        @Body body: Service
    ): Call<Service>

    @PATCH("servicos/cancelar/{idServico}")
    fun cancelUserService(@Path("idServico") serviceId: Long): Call<Service>
}

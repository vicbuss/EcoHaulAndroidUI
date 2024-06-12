package br.com.alura.ecohaulconnect.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.com.alura.ecohaulconnect.network.dtos.LoginBody
import br.com.alura.ecohaulconnect.network.dtos.ServiceData
import br.com.alura.ecohaulconnect.network.dtos.UserIdBody
import br.com.alura.ecohaulconnect.network.services.ApiClient
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.ID
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.LOGGEDIN
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.TOKEN
import kotlinx.coroutines.flow.firstOrNull

class EcoHaulRepository private constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val api = ApiClient.apiService

    companion object {
        @Volatile
        private var instance: EcoHaulRepository? = null

        fun getInstance(dataStore: DataStore<Preferences>): EcoHaulRepository {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = EcoHaulRepository(dataStore)
                    }
                }
            }
            return instance!!
        }
    }

    suspend fun login(user: String, password: String) {
        val loginBody = LoginBody(
            login = user,
            senha = password
        )
        // var token: String? = null
        val response = api.getLoginToken(loginBody)

        if (response.isSuccessful) {
            response.body()?.let {
                val token = it.token
                dataStore.edit { preferences ->
                    preferences[TOKEN] = token
                    preferences[LOGGEDIN] = true

                }
            }
        }
    }

    suspend fun getId(email: String) {
        val body = UserIdBody(email)

        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)

        token?.let { jwt ->
            val response = api.getUserId(body = body, token = "Bearer $jwt")
            if (response.isSuccessful) {
                response.body()?.let { idResponse ->
                    dataStore.edit { mutablePreferences ->
                        mutablePreferences[ID] = idResponse.id
                    }
                }
            } else {
                if (response.code() == 403) {
                    Log.e("EcoHaulRepository", "getId: Unauthorized")
                    //                   dataStore.edit {mutablePreferences ->
                    //                       mutablePreferences[LOGGEDIN] = false
                    //                       mutablePreferences[TOKEN] = ""
                } else {
                    Log.e("EcoHaulRepository", "getId: ${response.errorBody()}")
                }
            }
        }
    }

    suspend fun getServiceList(): List<ServiceData> {
        val preferences = dataStore.data.firstOrNull()
        val userId = preferences?.get(ID)
        val token = preferences?.get(TOKEN)

        var serviceDataList: List<ServiceData> = emptyList()

        userId?.let { id ->
            token?.let { jwt ->
                val response = api.getUserServices(userId = id, token = "Bearer $jwt")
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        serviceDataList = res.content
                    }
                } else {
                    Log.e("EcoHaulRepository", "getServiceList: ${response.errorBody()}")
                }
            }
        }
        return serviceDataList
    }

    suspend fun detailService(serviceId: Long): ServiceData? {
        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)

        var service: ServiceData? = null

        token?.let { jwt ->
            val response = api.detailService(serviceId = serviceId, token = "Bearer $jwt")

            if (response.isSuccessful) {
                response.body()?.let {
                    service = it
                }
            } else {
                Log.e("EcoHaulRepository", "getServiceList: ${response.errorBody()}")
            }
        }
        return service
    }
}
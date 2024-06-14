package br.com.connect.ecohaulconnect.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.com.connect.ecohaulconnect.model.Service
import br.com.connect.ecohaulconnect.model.toNewServiceData
import br.com.connect.ecohaulconnect.model.toServiceData
import br.com.connect.ecohaulconnect.network.dtos.LoginBody
import br.com.connect.ecohaulconnect.network.dtos.ServiceData
import br.com.connect.ecohaulconnect.network.dtos.UserData
import br.com.connect.ecohaulconnect.network.dtos.UserIdBody
import br.com.connect.ecohaulconnect.network.services.ApiClient
import br.com.connect.ecohaulconnect.preferences.PreferencesKey.ID
import br.com.connect.ecohaulconnect.preferences.PreferencesKey.LOGGEDIN
import br.com.connect.ecohaulconnect.preferences.PreferencesKey.TOKEN
import kotlinx.coroutines.flow.firstOrNull
import java.net.ConnectException

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
    suspend fun signup(loginData: LoginBody, userData: UserData) {

        try {
            val userSignup = api.signupUserData(loginData)
            if (userSignup.isSuccessful) {
                val clientSignup = api.signupClientData(userData)
                if(clientSignup.isSuccessful) {
                    Log.i("EcoHaulRepository", "signup: Successfully signed up user")
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "login: Connection error when connecting to api")
        }
    }
    suspend fun executeLogin(user: String, password: String) {
        val loginBody = LoginBody(
            login = user,
            senha = password
        )
        val userBody = UserIdBody(user)

        try {
            val tokenResponse = api.getLoginToken(loginBody)
            if (tokenResponse.isSuccessful) {
                tokenResponse.body()?.let {loginResponse ->
                    val token = loginResponse.token
                    val idResponse = api.getUserId(userBody, "Bearer $token")
                    if (idResponse.isSuccessful) {
                        idResponse.body()?.let {idResponse ->
                            val id = idResponse.id
                            dataStore.edit {preferences ->
                                preferences[TOKEN] = token
                                preferences[ID] = id
                                preferences[LOGGEDIN] = true
                            }
                        }
                    }
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "login: Connection error when connecting to api")
        }

    }

//    suspend fun getClientId(email: String) {
//        val body = UserIdBody(email)
//
//        val preferences = dataStore.data.firstOrNull()
//        val token = preferences?.get(TOKEN)
//
//        try {
//            token?.let { jwt ->
//                val response = api.getUserId(body = body, token = "Bearer $jwt")
//                if (response.isSuccessful) {
//                    response.body()?.let { idResponse ->
//                        dataStore.edit { mutablePreferences ->
//                            mutablePreferences[ID] = idResponse.id
//                        }
//                    }
//                } else {
//                    if (response.code() == 403) {
//                        Log.e("EcoHaulRepository", "getId: Unauthorized")
//                        dataStore.edit { preferences ->
//                            preferences[LOGGEDIN] = false
//                        }
//                    } else {
//                        Log.e("EcoHaulRepository", "getId: ${response.errorBody()}")
//                    }
//                }
//            }
//        } catch (e: ConnectException) {
//            Log.e("EcoHaulRepository", "getId: Connection error when connecting to api")
//        }
//
//    }

    suspend fun listServices(): List<ServiceData> {
        val preferences = dataStore.data.firstOrNull()
        val userId = preferences?.get(ID)
        val token = preferences?.get(TOKEN)

        var serviceDataList: List<ServiceData> = emptyList()

        try {
            userId?.let { id ->
                token?.let { jwt ->
                    val response = api.getUserServices(userId = id, token = "Bearer $jwt")
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            serviceDataList = res.content
                        }
                    } else {
                        if (response.code() == 403) {
                            dataStore.edit { preferences ->
                                preferences[LOGGEDIN] = false
                            }
                        } else {
                            Log.e("EcoHaulRepository", "getServiceList: ${response.errorBody()}")
                        }
                    }
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "getServiceList: Connection error when connecting to api")
        }

        return serviceDataList
    }

    suspend fun detailService(serviceId: Long): ServiceData? {
        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)

        var service: ServiceData? = null

        try {
            token?.let { jwt ->
                val response = api.detailService(serviceId = serviceId, token = "Bearer $jwt")

                if (response.isSuccessful) {
                    response.body()?.let {
                        service = it
                    }
                } else {
                    if (response.code() == 403) {
                        dataStore.edit { preferences ->
                            preferences[LOGGEDIN] = false
                        }
                    } else {
                        Log.e("EcoHaulRepository", "detailService: ${response.errorBody()}")
                    }
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "detailService: Connection error when connecting to api")
        }

        return service
    }

    suspend fun addNewService(service: Service): ServiceData? {
        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)
        val userId = preferences?.get(ID)
        Log.i("EcoHaulRepository", "addNewService: token = $token")
        Log.i("EcoHaulRepository", "addNewService: id = $userId")

        var addedService: ServiceData? = null

        try {
            token?.let { jwt ->
                userId?.let { clientId ->
                    val newServiceData = service.toNewServiceData(clientId = clientId)
                    val response = api.createService(body = newServiceData, token = jwt)

                    if (response.isSuccessful) {
                        Log.i("EcoHaulRepository", "addNewService: service created successfully = ${response.body()}")
                        addedService = response.body()
                    } else {
                        if (response.code() == 403) {
                            dataStore.edit { preferences ->
                                preferences[LOGGEDIN] = false
                            }
                        } else {
                            Log.e("EcoHaulRepository", "addNewService: ${response.errorBody()}")
                        }
                    }
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "addNewService: Connection error when connecting to api")
        }
        return addedService
    }

    suspend fun editService(service: Service): ServiceData? {
        Log.i("EcoHaulRepository", "editService: serviceId = $service.id")
        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)
        val userId = preferences?.get(ID)

        var editedService: ServiceData? = null

        try {
            token?.let { jwt ->
                userId?.let { clientId ->
                    val editServiceData = service.toServiceData(clientId)
                    val response = api.updateService(body = editServiceData, token = jwt, serviceId = service.id)

                    if (response.isSuccessful) {
                        editedService = response.body()
                    } else {
                        if (response.code() == 403) {
                            dataStore.edit { preferences ->
                                preferences[LOGGEDIN] = false
                            }
                        } else {
                            Log.e("EcoHaulRepository", "editService: ${response.errorBody()}")
                        }
                    }
                }
            }

        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "addNewService: Connection error when connecting to api")
        }
        return editedService
    }

    suspend fun removeService(serviceId: Long)  {
        val preferences = dataStore.data.firstOrNull()
        val token = preferences?.get(TOKEN)

        try {
            token?.let { jwt ->
                val response = api.cancelService(serviceId, token)
                if (!response.isSuccessful)  {
                    Log.e("EcoHaulRepository", "cancelService: ${response.errorBody()}")
                    if (response.code() == 403) {
                        dataStore.edit { preferences ->
                            preferences[LOGGEDIN] = false
                        }
                    }
                }
            }
        } catch (e: ConnectException) {
            Log.e("EcoHaulRepository", "addNewService: Connection error when connecting to api")
        }
    }


}
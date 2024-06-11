package br.com.alura.ecohaulconnect.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.com.alura.ecohaulconnect.network.dtos.LoginBody
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
        Log.i("EcoHaulRepository", "getId: called method")
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
                    Log.e("EcoHaulRepository", "getId: Error", )
                }
            }
        }
    }
}
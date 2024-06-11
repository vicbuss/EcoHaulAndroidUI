package br.com.alura.ecohaulconnect.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "login")

object PreferencesKey {
    val ID = longPreferencesKey(name = "id")
    val TOKEN = stringPreferencesKey(name = "token")
    val LOGGEDIN = booleanPreferencesKey("loggedIn")
}
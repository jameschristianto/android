package com.example.githubuserremake.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore private constructor(private val dataStore: DataStore<Preferences>) {
    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        @Volatile
        private var INSTANCE: UserDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = UserDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
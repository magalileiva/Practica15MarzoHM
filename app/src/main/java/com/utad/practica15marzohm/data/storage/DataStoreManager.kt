package com.utad.practica15marzohm.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="dataStore")
    private val userNameKey = stringPreferencesKey("USER_NAME_KEY")

    suspend fun saveData(context: Context, sampleData: String){
        context.dataStore.edit { editor ->
            editor[userNameKey] = sampleData
        }
    }
    suspend fun getData (context: Context): Flow<String> {
        return context.dataStore.data.map { editor ->
            editor [userNameKey] ?: "No hay datos"
        }
    }

    suspend fun deleteAll (context: Context){
        context.dataStore.edit { editor ->
            editor.clear() // borra todos los datos
        }
    }

    suspend fun deleteSample(context: Context){
        context.dataStore.edit { editor ->
            editor.remove(userNameKey) // borrar solo un dato
        }
    }
}
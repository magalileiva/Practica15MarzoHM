package com.utad.practica15marzohm.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.practica15marzohm.data.storage.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//guardamos los datos del usuario haciendo uso de nuestra DataStoreManager

class MainViewModel: ViewModel() {

    // en este caso solo quiero guardar un nombre, si quiero guardar mas cosas creo otras funciones

    fun saveData(context: Context, username: String){
        viewModelScope.launch (Dispatchers.IO){
            //uso la funcion de guardar datos del DataStore
            DataStoreManager.saveData(context, username)
        }
    }
}
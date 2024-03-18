package com.utad.practica15marzohm.ui.secondView

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.practica15marzohm.data.network.TareasHMApi
import com.utad.practica15marzohm.data.network.model.TareasHMItem
import com.utad.practica15marzohm.data.storage.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// creo la data class para las comprobaciones
data class HomeWorkUiState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val info: List<TareasHMItem>? = null // lo mismo que en el service*, si borre la clase que era una List, entonces aca recibo una List de la clase que quiero usar
)

class SeconActivityViewModel: ViewModel() {

    // en este caso guardaré nombre usuario, o lo que necesite
    private var _userName: MutableLiveData<String> = MutableLiveData(null)
    val userName: LiveData<String> = _userName

    private var _uiState: MutableLiveData<HomeWorkUiState> = MutableLiveData(HomeWorkUiState())
    val uiState: LiveData<HomeWorkUiState> get() = _uiState


    fun getUserName(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            // recibo los datos guardados en dataStoreManager
            DataStoreManager.getData(context).collect{ userName ->
                if(userName!="No hay datos"){
                    _userName.postValue(userName)
                }
            }
        }
    }
    // esta funcion sera la que cargue la API y el service, tengo que enviar lo que la funcion me pida
    // si no lo recuerdo lo verifico en el service
    fun getHomeWork(userName: String){

        _uiState.postValue(HomeWorkUiState(isLoading = true))

        viewModelScope.launch(Dispatchers.IO){
            // vuelvo al service para ver que parametros esta esperando la funcion
            val response = TareasHMApi.service.getAllTodo(userName, "Escuela")  // Uso la clase api y la service y la funcion del service
            if(response.isSuccessful){
                _uiState.postValue(HomeWorkUiState(info = response.body()))

            }else{
                _uiState.postValue(HomeWorkUiState(error = true))
            }
        }
    }
}
package com.utad.practica15marzohm.data.network

import com.utad.practica15marzohm.data.network.model.TareasHMItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TareasHMService {
    // 1 hago el get del endpoint, tiene que coincidir con el path de la funcion
    @GET("homeWork/{school}")
    // 2 creo la funcion que devuelva la clase original (es el nombre de la clase que colocamos al crear el Json)
    suspend fun getAllTodo(@Header("Authorization") auth: String, @Path("school") name: String): Response<List<TareasHMItem>>
}

// en el Response va a depender si contiene una Lista de si borro o no lo que se me crea en el model
// en este caso borre la clase que solo contenia la lista de tareas
// y use TareasItem que es la que contiene todos los atributos
// simplemente recibiendo en el Response una lista de la clase TareasItem

// en el Servicio hacemos el GET del endpoint, verificar siempre las " / " y agregar el Path en caso de ser necesario
// en la funcion coloco como parametro lo que pida el endpoint, ej: Path o Heater en caso de autorizaciones


/*Endpoint: “/homeWork/{nombre del colegio}”
Es una petición de tipo “GET”
Necesita una “Authorization” en la cabecera en la que enviéis el nombre del usuario.
Necesitáis mandar en el path el colegio (da igual el nombre)*/
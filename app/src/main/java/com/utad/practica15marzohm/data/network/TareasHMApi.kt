package com.utad.practica15marzohm.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TareasHMApi {
    //Conversor Json -> data class
    private val converter = GsonConverterFactory.create()

    // Intercepta las llamadas por consola
    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    // Acopla al cliente el interceptor / carga de interceptor
    private val client = OkHttpClient.Builder().addInterceptor(logginInterceptor).build()

    //Instancia de Retrofit - rest base URL
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ud5-server.onrender.com/api/v1/") // Siempre tiene que terminar en /
        .client(client)
        .addConverterFactory(converter)
        .build()

    // 1 A traves del service podemor llamar a las peticiones de red del servicio que creamos:  *Service
    val service: TareasHMService by lazy {
        retrofit.create(TareasHMService::class.java)
    }
}
// recordar CAMBIAR LA URL DE LA API!!!!
// 2 despues de crear mi API Manager tengo que crear el Service (que es una interfaz)
// 3 y a su vez crear el modelo de datos con el Json
// 4 ahora a crear el Adapter

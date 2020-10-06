package com.example.movieskotlin.retrofit

import com.example.movieskotlin.common.Constantes
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit : Retrofit

            companion object {   //patron singleton
                var instance: TheMovieDBClient? = null
                    get() {
                        if (field == null){
                            instance  = TheMovieDBClient()
                    }
                        return field
                    }
            }
    init {
        //Incluir el interceptor que hemos definido
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val cliente = okHttpClientBuilder.build()  //cliente que pasamos abajo

        //Construimos el cliente de retrofit
         retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMB_BASE_URL)   //le decimos cual es la url con la que vamos  atrabajar
             .addConverterFactory(GsonConverterFactory.create())         //le asociamos el tiop de conversor que queremos.
             .client(cliente)
             .build()

        //Instanciamos el servicio de retrofit a partir del objeto retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService()= theMovieDBService

}
package com.example.movieskotlin.retrofit

import com.example.movieskotlin.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //Añadimos parámetros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LANGUAGE, "es-ES")
            .build()

       //Tomamos la nueva URL y la asociamos a la url del a peticion
        var request = chain.request()       //variable que tome la peticion de nuevo

        request = request?.newBuilder()      //nuevo constructor con operador de llamada segura que si viene nulo el valor devuelve null (?)
            .url(urlWithParams)             //url modificada
            .addHeader("Content-Type", "application/json")      //indicamos que la peticion la enviamos en Json.
            .addHeader("Accept", "application/json")           //indicamos que aceptamos la cabecera accept de json.
            .build()

        return chain.proceed(request)   //que el request continue hacia el destino que iba dirijido.
    }

}
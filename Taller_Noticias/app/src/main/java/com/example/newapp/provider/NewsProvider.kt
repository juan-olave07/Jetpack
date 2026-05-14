package com.example.newapp.provider

import com.example.newapp.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Clave utilizada para acceder a la API de noticias
private const val API_KEY = "fe970170c1fe41cb81bd82c77c185ae0"

// Interfaz de Retrofit para consumir la API
interface NewsProvider {

    // Obtiene las noticias principales según el país indicado
    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun topHeadlines(@Query("country") country: String): Response<NewsApiResponse>
}

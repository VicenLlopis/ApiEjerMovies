package com.example.apiejermovies.data

import com.example.apiejermovies.data.model.MoviesJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ThemoviedbApi {
    @GET("popular")
    fun obtenerPopularPelicula(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MoviesJson>
}
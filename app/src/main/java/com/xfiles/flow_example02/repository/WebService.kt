package com.xfiles.flow_example02.repository

import com.google.gson.GsonBuilder
import com.xfiles.flow_example02.application.AppConstants
import com.xfiles.flow_example02.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): MovieList
}

object RetrofitClient {


    val webService by lazy {

        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}
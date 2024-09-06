package com.example.realtimeweatherapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v1/forecast.json")
    suspend fun getWeather(
        @Query("key") apikey : String,
        @Query("q") city:String
    ) :Response<WeatherModel>
}
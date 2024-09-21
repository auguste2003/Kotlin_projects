package com.example.finddog.data.remote.RetrofitInstance.kt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val url = "https://api.thecatapi.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dogAPI : DogAPI = retrofit.create(DogAPI::class.java)

}
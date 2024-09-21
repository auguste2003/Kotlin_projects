package com.example.finddog.data.remote.RetrofitInstance.kt

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogAPI {

    @GET("/v1/images/search")
    suspend fun getDogs(
        @Query("limit") limit : Int = 10
    ): Response<ArrayList<DogResponceItem>>
}
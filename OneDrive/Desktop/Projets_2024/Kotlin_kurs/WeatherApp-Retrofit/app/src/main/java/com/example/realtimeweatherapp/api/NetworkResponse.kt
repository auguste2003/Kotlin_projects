package com.example.realtimeweatherapp.api

// T refere á Weathermodel
sealed class NetworkResponse <out T> {

    data class Success<out T>(val data:T):NetworkResponse<T>()
    data class Error(val message : String): NetworkResponse<Nothing>()
     object Loading :NetworkResponse<Nothing>()

}
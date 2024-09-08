package com.example.realtimeweatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweatherapp.api.ConstantKey
import com.example.realtimeweatherapp.api.NetworkResponse
import com.example.realtimeweatherapp.api.RetrofitInstance
import com.example.realtimeweatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
private  val weatherAPI = RetrofitInstance.weatherAPI
private  val _weatherResult  = MutableLiveData<NetworkResponse<WeatherModel>>()
 val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult  // ON espose ce model á l'ui .

    fun getData(city:String){

  viewModelScope.launch {
          _weatherResult.value = NetworkResponse.Loading
      try {

          val response = weatherAPI.getWeather(ConstantKey.apiKey, city)
          if (response.isSuccessful) {
              response.body()?.let {
                  _weatherResult.value = NetworkResponse.Success(it)
              }
              //   Log.i("Response ",response.body().toString())
          } else {
              _weatherResult.value = NetworkResponse.Error("Failed to loead data ")
              //    Log.i("Error ",response.message())
          }
      }
      catch (e:Exception){
          _weatherResult.value = NetworkResponse.Error("Failed to loead data ")
      }
  }

       //  Log.i("City name ",city,)
    }
}
package com.example.finddog.DogViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finddog.data.remote.RetrofitInstance.kt.DogResponce
import com.example.finddog.data.remote.RetrofitInstance.kt.DogResponceItem
import com.example.finddog.data.remote.RetrofitInstance.kt.RetrofitInstance
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DogViewModel:ViewModel() {

    val dogAPI = RetrofitInstance.dogAPI

    val dogResult :MutableLiveData<ArrayList<DogResponceItem>> = MutableLiveData<ArrayList<DogResponceItem>>()
    val _dogResult : LiveData<ArrayList<DogResponceItem>> = dogResult
     var status : MutableLiveData<Status> = MutableLiveData()

    var selectedDog by mutableStateOf<DogResponceItem?>(value = null)

    init {
        getDogs()
    }

    fun setDog(dog:DogResponceItem){
        viewModelScope.launch {
            selectedDog = dog
        }

    }




    fun getDogs(){
        viewModelScope.launch {
            status.value = Status.loading
            try {

                val response= dogAPI.getDogs()
                if(response.isSuccessful){
                    status.value = Status.success


                    response.body()?.let {
                        dogResult.value = it
                    }

                    Log.i("dogs", " Dogs retrieved ${dogResult.value.toString()}")
                }else {
                    status.value = Status.error
                    Log.i("error_response", "getDogs: Error in response code   ")
                }

            }catch (e:IllegalAccessException){
                status.value = Status.error
                Log.i("error_get", "getDogs: Error connecting the api  ")
            }
        }
    }
}
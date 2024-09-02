package com.example.compose_kurs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel :ViewModel(){
    // var counter = 0 // State of the counter
    var counter = MutableLiveData<Int>(0) // rememberSaveable  la valeur ne change pas apres orientation
    val error = MutableLiveData<String>()

    var increaseCounter ={
        Log.i( "MainScreen: ","Counter value ist ${counter}")
        counter.value =  counter.value?.plus(1)// On incrémente si ce n'ai pas null
    }
    var decrementCounter = {
        if (counter.value ==0){
            if(error.value == null){
                error.value ="Counter can not be lass than 0"
            }
        }else {
            Log.i("MainScreen: ", "Counter value ist ${counter}")
            counter.value = counter.value?.minus(1)
        }
    }
}
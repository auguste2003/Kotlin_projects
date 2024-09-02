package com.example.compose_kurs.componets.Scroffold.screens.data

import kotlinx.serialization.Serializable

@Serializable
data class Student (val name : String,
               val age : Int,val rollNo:Int,val standard:Int){
}
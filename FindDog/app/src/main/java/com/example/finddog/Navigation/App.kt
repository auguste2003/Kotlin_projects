package com.example.finddog.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finddog.DogViewModel.DogViewModel
import com.example.finddog.ui.theme.DogDetail
import com.example.finddog.ui.theme.HomeScreen


@Composable
fun App(){
    val navController = rememberNavController()
    val viewModel : DogViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home" ){
        composable("home") {
            HomeScreen(dogViewModel = viewModel, navController =navController )
        }
        composable("dogDetails") {
            DogDetail(dogViewModel = viewModel, navController =navController )
        }
    }
}
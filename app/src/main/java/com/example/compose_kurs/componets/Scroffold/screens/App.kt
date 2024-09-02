import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable // Import de la fonction `composable`
import androidx.navigation.compose.NavHost // Import de `NavHost`
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.compose_kurs.componets.Scroffold.screens.ScreenOne
import com.example.compose_kurs.componets.Scroffold.screens.ScreenThree
import com.example.compose_kurs.componets.Scroffold.screens.ScreenTwo

// NavHost
// NavController
// NavGraph
// Destination


// NavHost
@Composable
fun App(){
    val navController = rememberNavController()  // Use to contrll the navigation

    navController.addOnDestinationChangedListener{
            controller,
            destination,
            arguments->
        Log.i("NavController","Destination : ${destination.route}")
        Log.i("NavController","Controller : ${controller.currentDestination?.route}")
    }
 //   NavHost(navController = navController, startDestination = "screen-one"){ //
 //       navigationGraph(navController =navController)
  //  }
    NavHost(navController = navController, graph =getMyNavGraoh(navController) )
}


// Définition du NavGraph
fun NavGraphBuilder.navigationGraph(navController: NavController) {
    composable("screen-one") {
        ScreenOne(navController)
    }
    composable("screen-two/{data}") {
        val data = it.arguments?.getString("data") ?: "No Data availabe"
        //
        navController.currentBackStackEntry?.arguments?.apply {
            putString("data",data)
        }
        ScreenTwo(navController)
     //   ScreenTwo(navController, data)
    }
    composable("screen-three") {
        ScreenThree(navController)
    }
}

fun getMyNavGraoh(controller: NavController) : NavGraph{
  return  controller.createGraph(startDestination = "screen-one"){
        composable("screen-one") {
            ScreenOne(controller)
        }
      composable("screen-two/{data}") {
          val data = it.arguments?.getString("data") ?: "No Data availabe"
          // Récup´rer directement la donnée sans passer un parametre
          controller.currentBackStackEntry?.arguments?.apply {
              putString("data",data)
          }
          ScreenTwo(controller)
          //   ScreenTwo(navController, data)
      }
        composable("screen-three") {
            ScreenThree(controller)
        }
    }
}
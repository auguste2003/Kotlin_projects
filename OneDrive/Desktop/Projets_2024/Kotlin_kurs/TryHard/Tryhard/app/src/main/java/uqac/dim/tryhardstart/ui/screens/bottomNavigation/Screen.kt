package uqac.dim.tryhardstart.ui.screens.bottomNavigation

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import uqac.dim.tryhardstart.R

sealed class Screen(val route: String, @DrawableRes val icon: Int, val title: String) {
    data object Home : Screen("Startseite", R.drawable.baseline_home_24, "Startseite")
    data object Trajet : Screen("Fahrt", R.drawable.baseline_swap_calls_24, "Fahrt")
    data object Ticket: Screen("Ticket", R.drawable.baseline_book_online_24, "Ticket")
    data object Profile : Screen("Benutzer", R.drawable.baseline_person_24, "Benutzer")


}

package uqac.dim.tryhardstart.ui.screens.codeotp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

@Composable

fun PageOtp(navController: NavController, signupViewModel: SignupViewModel ,businessAccountViewModel: BusinessAccountViewModel){
    Column {
        Header()
        Spacer(modifier = Modifier.height(30.dp))
        CodeVerify(navController,signupViewModel,businessAccountViewModel)
    }
}
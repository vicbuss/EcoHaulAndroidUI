package br.com.connect.ecohaulconnect.navigation

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.connect.ecohaulconnect.ui.screens.SignupFormScreen
import br.com.connect.ecohaulconnect.ui.viewModels.SignupScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.signupGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.SignupForm.route
    ) {
        val application = LocalContext.current.applicationContext as Application
        val viewModel: SignupScreenViewModel = viewModel(factory = EcoHaulViewModelFactory(application = application))
        val state by viewModel.uiState.collectAsState()

        SignupFormScreen(
            state = state,
            onSave = {
                viewModel.signup()
                navController.navigate(AppDestinations.Login.route)
            }
        )
    }
}
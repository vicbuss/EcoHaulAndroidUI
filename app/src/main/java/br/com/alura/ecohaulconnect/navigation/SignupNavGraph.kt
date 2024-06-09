package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.ui.screens.SignupFormScreen
import br.com.alura.ecohaulconnect.ui.viewModels.SignupScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.signupGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.SignupForm.route
    ) {
        val viewModel: SignupScreenViewModel = viewModel(factory = EcoHaulViewModelFactory())
        val state by viewModel.uiState.collectAsState()

        SignupFormScreen(
            state = state,
            onSave = {
                navController.navigate(AppDestinations.Login.route)
            }
        )
    }
}
package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.ui.screens.LoginScreen
import br.com.alura.ecohaulconnect.ui.viewModels.LoginScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.Login.route
    ) {
        val viewModel: LoginScreenViewModel = viewModel(factory = EcoHaulViewModelFactory())
        val state by viewModel.uiState.collectAsState()

        LoginScreen(
            state = state,
            onClickLogin = {
                navController.navigate(AppDestinations.HomeGraph.route)
            },
            onClickSignUp = {
                navController.navigate(AppDestinations.SignupForm.route)
            }
        )
    }
}
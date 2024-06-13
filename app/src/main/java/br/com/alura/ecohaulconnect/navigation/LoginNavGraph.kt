package br.com.alura.ecohaulconnect.navigation

import android.app.Application
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateStraight
import br.com.alura.ecohaulconnect.ui.screens.LoginScreen
import br.com.alura.ecohaulconnect.ui.viewModels.LoginScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.Login.route
    ) {
        val application = LocalContext.current.applicationContext as Application
        val viewModel: LoginScreenViewModel =
            viewModel(factory = EcoHaulViewModelFactory(application = application))
        val state by viewModel.uiState.collectAsState()

        if (state.isLoggedIn) {
            LaunchedEffect(Unit) {
                navController.navigate(AppDestinations.SplashScreen.route)
            }
        }
        LoginScreen(
            state = state,
            onClickLogin = {
                viewModel.login()
            },
            onClickSignUp = {
                navController.navigate(AppDestinations.SignupForm.route)
            }
        )
    }
}
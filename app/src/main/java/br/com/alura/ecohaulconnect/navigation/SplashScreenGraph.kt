package br.com.alura.ecohaulconnect.navigation

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateStraight
import br.com.alura.ecohaulconnect.ui.state.AppState
import br.com.alura.ecohaulconnect.ui.viewModels.SplashScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.splashGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.SplashScreen.route
    ) {

        val application = LocalContext.current.applicationContext as Application
        val viewModel: SplashScreenViewModel = viewModel(factory = EcoHaulViewModelFactory(application = application))
        val state by viewModel.uiState.collectAsState()

        when (state.appState) {
            AppState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            AppState.LoggedOut -> {
                LaunchedEffect(Unit) {
                    navController.navigateStraight(AppDestinations.Login.route)
                }
            }
            AppState.LoggedIn -> {
                LaunchedEffect(Unit) {
                    navController.navigateStraight(AppDestinations.HomeGraph.route)
                }
            }
        }
    }
}
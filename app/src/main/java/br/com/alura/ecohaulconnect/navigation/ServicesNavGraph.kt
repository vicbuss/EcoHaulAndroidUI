package br.com.alura.ecohaulconnect.navigation

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alura.ecohaulconnect.navigateIfAuthorized
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.ui.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceListScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.servicesGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = AppDestinations.Services.route,
        route = AppDestinations.HomeGraph.route
    ) {
        composable(AppDestinations.Services.route) {
            val dataStore = LocalContext.current.datastore
            val coroutineScope = rememberCoroutineScope()

            val application = LocalContext.current.applicationContext as Application
            val viewModel: ServiceListScreenViewModel =
                viewModel(factory = EcoHaulViewModelFactory(application = application))
            val state by viewModel.uiState.collectAsState()


            ServiceListScreen(
                state = state,
                onNavigateToServiceDetails = { serviceId ->
                    coroutineScope.launch {
                        navController.navigateIfAuthorized("${ServiceDetails.route}/$serviceId", dataStore)
                    }
                },
                onBottomNavBarSelectedItemChange = {
                    val route = it.route
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(route)
                    }
                }
            )
        }
    }
}


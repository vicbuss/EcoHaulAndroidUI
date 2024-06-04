package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import br.com.alura.ecohaulconnect.navigateToServiceDetails
import br.com.alura.ecohaulconnect.ui.components.onBottomNavBarSelectedItemChange
import br.com.alura.ecohaulconnect.ui.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceListScreenViewModel

fun NavGraphBuilder.servicesGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = AppDestinations.Services.route,
        route = AppDestinations.HomeGraph.route
    ) {
        composable(AppDestinations.Services.route) {
            val viewModel: ServiceListScreenViewModel =
                viewModel(factory = EcoHaulViewModelFactory())
            val state by viewModel.uiState.collectAsState()

            ServiceListScreen(
                state = state,
                onNavigateToServiceDetails = { serviceId ->
                    navController.navigate("${ServiceDetails.route}/$serviceId")
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


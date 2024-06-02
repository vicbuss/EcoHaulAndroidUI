package br.com.alura.ecohaulconnect.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alura.ecohaulconnect.navigateToServiceDetails
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.screens.ServiceListScreen

fun NavGraphBuilder.servicesGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AppDestinations.Services.route,
        route = AppDestinations.HomeGraph.route
    ) {
        composable(AppDestinations.Services.route) {
            // refactor
            val services = sampleServiceList
            ServiceListScreen(
                services = services,
                onNavigateToServiceDetails = { service ->
                    navController.navigateToServiceDetails(service.id)
                }
            )
        }
    }
}


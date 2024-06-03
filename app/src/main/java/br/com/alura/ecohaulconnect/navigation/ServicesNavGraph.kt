package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alura.ecohaulconnect.data.ServiceDaoFactory
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.navigateToServiceDetails
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.screens.ServiceListUiState

fun NavGraphBuilder.servicesGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AppDestinations.Services.route,
        route = AppDestinations.HomeGraph.route
    ) {
        composable(AppDestinations.Services.route) {
            // refactor
            val dao = ServiceDaoFactory.getDao("memory")
            val services = dao.listServices()
            if (services.isEmpty()) {
                dao.addService(sampleService)
            }
            val onNavigateToServiceDetails =
                { service: Service -> navController.navigateToServiceDetails(service.id) }
            val state = remember(services) {
                ServiceListUiState(
                    services = services,
                    onNavigateToServiceDetails = onNavigateToServiceDetails
                )
            }
            ServiceListScreen(
                state = state
            )
        }
    }
}


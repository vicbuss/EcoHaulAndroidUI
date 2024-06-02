package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateToServiceForm
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.screens.ServiceDetailsScreen

fun NavGraphBuilder.serviceDetailsGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceDetails.routeWithArgs,
        arguments = ServiceDetails.args
    ) { navBackstackEntry ->
        navBackstackEntry.arguments?.getLong(SERVICE_ID)?.let {id ->
            val services = sampleServiceList
            services.find { service ->
                service.id == id
            }?.let { service ->
                ServiceDetailsScreen(
                    service = service,
                    onCancelService = {
                        // dao.removeService(service)
                        navController.popBackStack()
                    },
                    onEditService = {
                        navController.navigateToServiceForm(it.id)
                    }
                )
            } ?: LaunchedEffect(Unit) { navController.popBackStack() }
        }

    }
}
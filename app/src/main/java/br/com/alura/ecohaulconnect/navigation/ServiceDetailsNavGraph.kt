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
import br.com.alura.ecohaulconnect.navigateToServiceForm
import br.com.alura.ecohaulconnect.ui.screens.ServiceDetailsScreen
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceDetailsScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory

fun NavGraphBuilder.serviceDetailsGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceDetails.routeWithArgs,
        arguments = ServiceDetails.args
    ) { navBackstackEntry ->
        navBackstackEntry.arguments?.getLong(SERVICE_ID)?.let { id ->
            val application = LocalContext.current.applicationContext as Application
            val viewModel: ServiceDetailsScreenViewModel = viewModel(
                factory = EcoHaulViewModelFactory(serviceId = id, application = application)
            )
            val state by viewModel.uiState.collectAsState()

            ServiceDetailsScreen(
                state = state,
                onEditService = {
                    navController.navigateToServiceForm(it.id)
                },
                onCancelService = {
                    viewModel.removeService()
                    navController.navigate(AppDestinations.Services.route)
                },
                onClickArrowBack = { navController.navigate(AppDestinations.Services.route) }
            )

        } ?: LaunchedEffect(Unit) { navController.popBackStack() }
    }
}
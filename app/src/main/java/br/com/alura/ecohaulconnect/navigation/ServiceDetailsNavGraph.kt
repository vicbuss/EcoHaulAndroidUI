package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            val viewModel: ServiceDetailsScreenViewModel =
                viewModel(factory = EcoHaulViewModelFactory(id))
            val state by viewModel.uiState.collectAsState()
            state.service?.let {
                ServiceDetailsScreen(
                    state = state,
                    onEditService = {
                        navController.navigateToServiceForm(it.id)
                    },
                    onCancelService = {
                        viewModel.removeService()
                        navController.popBackStack()
                    },
                    onClickArrowBack = {navController.popBackStack()}
                )
            } ?: LaunchedEffect(Unit) { navController.popBackStack() }
        }
    }
}
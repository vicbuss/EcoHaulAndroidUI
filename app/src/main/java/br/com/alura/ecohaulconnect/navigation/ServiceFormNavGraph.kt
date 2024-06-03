package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.ui.screens.ServiceFormScreen
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceFormScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceFormScreenViewModelFactory

fun NavGraphBuilder.serviceFormGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceForm.routeWithArgs,
        arguments = ServiceForm.args
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(SERVICE_ID)?.let { id ->
            val viewModel: ServiceFormScreenViewModel = viewModel(factory = ServiceFormScreenViewModelFactory(id))
            val state by viewModel.uiState.collectAsState()
            ServiceFormScreen(
                state = state,
                onClickSave = {
                    viewModel.createOrEditService()
                    navController.popBackStack()
                }
            )
        }
    }
}
package br.com.connect.ecohaulconnect.navigation

import android.app.Application
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.connect.ecohaulconnect.navigateIfAuthorized
import br.com.connect.ecohaulconnect.preferences.datastore
import br.com.connect.ecohaulconnect.ui.screens.ServiceDetailsScreen
import br.com.connect.ecohaulconnect.ui.viewModels.ServiceDetailsScreenViewModel
import br.com.connect.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.serviceDetailsGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceDetails.routeWithArgs,
        arguments = ServiceDetails.args
    ) { navBackstackEntry ->
        navBackstackEntry.arguments?.getLong(SERVICE_ID)?.let { id ->
            val dataStore = LocalContext.current.datastore
            val coroutineScope = rememberCoroutineScope()
            val application = LocalContext.current.applicationContext as Application
            val viewModel: ServiceDetailsScreenViewModel = viewModel(
                factory = EcoHaulViewModelFactory(serviceId = id, application = application)
            )
            val state by viewModel.uiState.collectAsState()

            ServiceDetailsScreen(
                state = state,
                onEditService = {
                    coroutineScope.launch {
                        navController.navigateIfAuthorized("${ServiceForm.route}/${it.id}", dataStore)
                    }
                },
                onCancelService = {
                    viewModel.removeService()
                   coroutineScope.launch {
                       navController.navigateIfAuthorized(AppDestinations.Services.route, dataStore)
                   }
                },
                onClickArrowBack = {
                    coroutineScope.launch {
                        navController.navigateIfAuthorized(AppDestinations.Services.route, dataStore)
                    }
                }
            )

        } ?: LaunchedEffect(Unit) { navController.popBackStack() }
    }
}
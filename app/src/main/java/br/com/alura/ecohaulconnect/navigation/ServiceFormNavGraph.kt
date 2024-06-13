package br.com.alura.ecohaulconnect.navigation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateIfAuthorized
import br.com.alura.ecohaulconnect.navigateToServiceDetails
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.ui.screens.ServiceFormScreen
import br.com.alura.ecohaulconnect.ui.viewModels.ServiceFormScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.serviceFormGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceForm.routeWithArgs,
        arguments = ServiceForm.args
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(SERVICE_ID)?.let { id ->
            val dataStore = LocalContext.current.datastore
            val coroutineScope = rememberCoroutineScope()
            val application = LocalContext.current.applicationContext as Application
            val viewModel: ServiceFormScreenViewModel = viewModel(factory = EcoHaulViewModelFactory(serviceId = id, application = application))
            val state by viewModel.uiState.collectAsState()

            LaunchedEffect(state.id) {
                Log.i("ServiceFormNavGraph", "serviceFormGraph: try to navigate = ${state.id}")
                if (state.id != 0L) {
                    coroutineScope.launch {
                        navController.navigateIfAuthorized("${ServiceDetails.route}/${state.id}", dataStore)
                    }
                }
            }
            ServiceFormScreen(
                state = state,
                onClickSave = {
                    viewModel.createOrEditService()
                    // navController.navigateToServiceDetails(state.serviceId)
                },
                onClickArrowBack = {navController.navigateUp()},
                onBottomNavBarSelectedItemChange = {
                    val route = it.route
                    navController.navigate(route) {
                        coroutineScope.launch {
                            navController.navigateIfAuthorized(route, dataStore)
                        }

                    }
                }
            )
        }
    }
}
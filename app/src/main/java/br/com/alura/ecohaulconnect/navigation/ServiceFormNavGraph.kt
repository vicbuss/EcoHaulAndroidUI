package br.com.alura.ecohaulconnect.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.screens.EditServiceFormScreen

fun NavGraphBuilder.serviceFormGraph(
    navController: NavHostController
) {
    composable(
        route = ServiceForm.routeWithArgs,
        arguments = ServiceForm.args
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(SERVICE_ID)?.let { id ->
            // Change
            val service = sampleService
            EditServiceFormScreen(
                serviceToEdit = service,
                onEditService = {
                    //call method to save
                    navController.popBackStack()
                }
            )
        }
    }
}
package br.com.alura.ecohaulconnect.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.notificationsGraph(
    navController: NavHostController
) {
    composable(AppDestinations.Notifications.route) {
        Text(text = "Placeholder: Notifications screen")
    }
}
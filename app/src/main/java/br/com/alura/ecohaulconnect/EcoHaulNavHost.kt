package br.com.alura.ecohaulconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.alura.ecohaulconnect.navigation.AppDestinations
import br.com.alura.ecohaulconnect.navigation.ServiceDetails
import br.com.alura.ecohaulconnect.navigation.ServiceForm
import br.com.alura.ecohaulconnect.navigation.loginGraph
import br.com.alura.ecohaulconnect.navigation.notificationsGraph
import br.com.alura.ecohaulconnect.navigation.serviceDetailsGraph
import br.com.alura.ecohaulconnect.navigation.serviceFormGraph
import br.com.alura.ecohaulconnect.navigation.servicesGraph
import br.com.alura.ecohaulconnect.navigation.signupGraph


@Composable
fun EcoHaulNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        // startDestination = AppDestinations.HomeGraph.route,
        startDestination = AppDestinations.Login.route,
        modifier = modifier
    ) {
        loginGraph(navController)
        signupGraph(navController)
        servicesGraph(navController)
        serviceDetailsGraph(navController)
        serviceFormGraph(navController)
        notificationsGraph(navController)
    }
}

fun NavHostController.navigateStraight(route: String) = this.navigate(route) {
    popUpTo(this@navigateStraight.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateAndClear(route: String) = this.navigate(route) {
    popUpTo(0)
}

fun NavHostController.navigateToServiceDetails(serviceId: Long) {
    navigateStraight("${ServiceDetails.route}/$serviceId")
}

fun NavHostController.navigateToServiceForm(serviceId: Long) {
    this.navigate("${ServiceForm.route}/$serviceId")
}
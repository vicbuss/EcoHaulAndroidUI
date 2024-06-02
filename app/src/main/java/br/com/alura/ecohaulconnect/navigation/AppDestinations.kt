package br.com.alura.ecohaulconnect.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.alura.ecohaulconnect.components.NavBarItem

sealed class AppDestinations(val route: String, val title: String) {
    object Services: AppDestinations("services", "Meus serviços")
    // object ServiceDetails: AppDestinations("serviceDetails", "Detalhes")
    // object AddService: AppDestinations("addService", "Novo serviço")
    // object EditService: AppDestinations("editService", "Editar serviço")
    object Notifications: AppDestinations("notifications", "Notificações")
    object HomeGraph: AppDestinations("homeGraph", title = "EcoHaul")

}



const val SERVICE_ID = "serviceId"
object ServiceDetails {
    const val route = "serviceDetails"
    const val title = "Detalhes"
    const val routeWithArgs = "$route/{$SERVICE_ID}"
    val args = listOf(navArgument(SERVICE_ID) {
        type = NavType.LongType
        defaultValue = 0L
    })
}

object ServiceForm {
    const val route = "serviceForm"
    const val title = "Novo Serviço"
    const val routeWithArgs = "$route/{$SERVICE_ID}"
    val args = listOf(navArgument(SERVICE_ID) {
        type = NavType.LongType
        defaultValue = 0L
    })
}

val bottomAppBarItems = listOf<NavBarItem>(
    NavBarItem(
        label = AppDestinations.Services.title,
        icon = Icons.Filled.Home,
        route = AppDestinations.Services.route
    ),
    NavBarItem(
        label = ServiceForm.title,
        icon = Icons.Filled.AddCircle,
        route = ServiceForm.route
    ),
    NavBarItem(
        label = AppDestinations.Notifications.title,
        icon = Icons.Filled.Notifications,
        route = AppDestinations.Notifications.route
    )
)

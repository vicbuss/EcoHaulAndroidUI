package br.com.alura.ecohaulconnect.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import br.com.alura.ecohaulconnect.components.NavBarItem

sealed class AppDestinations(val route: String, val title: String) {
    object Services: AppDestinations("services", "Meus serviços")
    object ServiceDetails: AppDestinations("serviceDetails", "Detalhes")
    object AddService: AppDestinations("addService", "Novo serviço")
    object Notifications: AppDestinations("notifications", "Notificações")

}

val bottomAppBarItems = listOf<NavBarItem>(
    NavBarItem(
        label = "Meus serviços",
        icon = Icons.Filled.Home,
        route = "services"
    ),
    NavBarItem(
        label = "Novo serviço",
        icon = Icons.Filled.AddCircle,
        route = "addService"
    ),
    NavBarItem(
        label = "Notificações",
        icon = Icons.Filled.Notifications,
        route = "notifications"
    )
)

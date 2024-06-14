package br.com.connect.ecohaulconnect.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.connect.ecohaulconnect.ui.components.NavBarItem

sealed class AppDestinations(val route: String, val title: String) {
    object Services: AppDestinations("services", "Meus serviços")
    object Notifications: AppDestinations("notifications", "Notificações")
    object HomeGraph: AppDestinations("homeGraph", title = "EcoHaul")
    object Login: AppDestinations("login", title = "Login")
    object SignupForm: AppDestinations("signup", title = "Cadastrar")
    object SplashScreen: AppDestinations("splash", title = "Carregando")
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
        route = "${ServiceForm.route}/${0L}"
    ),
//    NavBarItem(
//        label = AppDestinations.Notifications.title,
//        icon = Icons.Filled.Notifications,
//        route = AppDestinations.Notifications.route
//    )
)

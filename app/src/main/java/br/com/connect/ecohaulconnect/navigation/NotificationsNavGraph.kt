package br.com.connect.ecohaulconnect.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.connect.ecohaulconnect.ui.components.EcoHaulScaffolding

fun NavGraphBuilder.notificationsGraph(
    navController: NavHostController
) {
    composable(AppDestinations.Notifications.route) {
        EcoHaulScaffolding(
            topBarTitle = "Notificações",
            showBottomBar = true,
            selectedBottomNavBarItem = bottomAppBarItems[2],
            onClickArrowBack = {navController.popBackStack()},
            onBottomNavBarSelectedItemChange = {
                val route = it.route
                navController.navigate(route) {
                    launchSingleTop = true
                    popUpTo(route)
                }
            }
        ) {
            Text(text = "Placeholder: Notifications screen")
        }
    }
}
package br.com.connect.ecohaulconnect.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import br.com.connect.ecohaulconnect.navigation.bottomAppBarItems
import br.com.connect.ecohaulconnect.ui.theme.EcoHaulConnectTheme
import br.com.connect.ecohaulconnect.ui.theme.Gray90
import br.com.connect.ecohaulconnect.ui.theme.Gray94

class NavBarItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun EcoHaulBottomNavBar(
    selectedItem: NavBarItem,
    modifier: Modifier = Modifier,
    items: List<NavBarItem> = emptyList(),
    onItemChange: (NavBarItem) -> Unit = {}

) {
    NavigationBar(containerColor = Gray94) {
        items.forEach { item ->
            val label = item.label
            val icon = item.icon

            NavigationBarItem(
                selected = selectedItem.label == label,
                onClick = { onItemChange(item) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                    )
                },
                label = { Text(text = label) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Gray90)
            )
        }
    }
}

@Preview
@Composable
fun EcoHaulBottomNavBarPreview() {
    EcoHaulConnectTheme {
        EcoHaulBottomNavBar(
            selectedItem = bottomAppBarItems.first(),
            items = bottomAppBarItems
        )
    }
}

fun onBottomNavBarSelectedItemChange(item: NavBarItem, navHost: NavHostController) {
    val route = item.route
    navHost.navigate(route) {
        launchSingleTop = true
        popUpTo(route)
    }
}
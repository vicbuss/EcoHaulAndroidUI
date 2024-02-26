package br.com.alura.ecohaulconnect.components

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.sampledata.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.theme.EcoHaulConnectTheme
import br.com.alura.ecohaulconnect.ui.theme.Gray84
import br.com.alura.ecohaulconnect.ui.theme.Gray90
import br.com.alura.ecohaulconnect.ui.theme.Gray94
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.Green90

class NavBarItem(
    val label: String,
    val icon: ImageVector,
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
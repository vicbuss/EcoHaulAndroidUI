package br.com.alura.ecohaulconnect.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import br.com.alura.ecohaulconnect.navigation.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.theme.White96


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcoHaulScaffolding (
    showNavigationIcon: Boolean = true,
    onClickArrowBack: () -> Unit = {},
    topBarTitle: String = "EcoHaul",
    showBottomBar: Boolean = false,
    selectedBottomNavBarItem: NavBarItem = bottomAppBarItems.first(),
    onBottomNavBarSelectedItemChange: (NavBarItem) -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (showNavigationIcon) {
                        IconButton(onClick = { onClickArrowBack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "voltar"
                            )
                        }
                    }
                },
                title = {
                    Text(
                        text = topBarTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White96,
                )
            )
        },
        bottomBar = {
            if (showBottomBar) {
                EcoHaulBottomNavBar(
                    selectedItem = selectedBottomNavBarItem,
                    items = bottomAppBarItems,
                    onItemChange = onBottomNavBarSelectedItemChange
                )
            }

        },
    ) {
        Box(Modifier.padding(it)) {
            content()
        }
    }
}
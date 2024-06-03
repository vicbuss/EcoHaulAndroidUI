package br.com.alura.ecohaulconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.ecohaulconnect.ui.components.EcoHaulBottomNavBar
import br.com.alura.ecohaulconnect.ui.components.NavBarItem
import br.com.alura.ecohaulconnect.data.ServiceDaoFactory
import br.com.alura.ecohaulconnect.navigation.AppDestinations
import br.com.alura.ecohaulconnect.navigation.ServiceDetails
import br.com.alura.ecohaulconnect.navigation.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.theme.EcoHaulConnectTheme
import br.com.alura.ecohaulconnect.ui.theme.White96

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination
            val dao = ServiceDaoFactory.getDao("memory")
            val services = dao.listServices()
            EcoHaulConnectTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = White96) {
                    val selectedItem by remember(currentDestination) {
                        val item = currentDestination?.let { destination ->
                            bottomAppBarItems.find {
                                it.route == destination.route
                            }
                        } ?: bottomAppBarItems.first()
                        mutableStateOf(item)
                    }
                    val isContainedInBottomBarItems = currentDestination?.let { destination ->
                        bottomAppBarItems.find { item ->
                            destination.route == item.route
                        }
                    } != null
                    val isShowNavigationIcon = when (currentDestination?.route) {
                        AppDestinations.Services.route -> false
                        else -> true
                    }
                    val serviceId = backStackEntryState?.arguments?.getLong("serviceId")
                    val service = services.find { service ->
                        service.id == serviceId
                    }
                    val topBarTitle = when (currentDestination?.route) {
                        AppDestinations.Services.route -> AppDestinations.Services.title
                        // AppDestinations.AddService.route -> AppDestinations.AddService.title
                        AppDestinations.Notifications.route -> AppDestinations.Notifications.title
                        "${ServiceDetails.route}/{serviceId}" -> service?.description
                            ?: ServiceDetails.title

                        else -> "EcoHaul Connect"
                    }
                    App(
                        navController = navController,
                        selectedBottomNavBarItem = selectedItem,
                        onBottomNavBarSelectedItemChange = {
                            val route = it.route
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(route)
                            }
                        },
                        topBarTitle = topBarTitle,
                        isShowNavigationIcon = isShowNavigationIcon,
                        isShowBottomBar = isContainedInBottomBarItems
                    ) {
                        EcoHaulNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    navController: NavController,
    selectedBottomNavBarItem: NavBarItem = bottomAppBarItems.first(),
    onBottomNavBarSelectedItemChange: (NavBarItem) -> Unit = {},
    topBarTitle: String = "",
    isShowNavigationIcon: Boolean = false,
    isShowBottomBar: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (isShowNavigationIcon) {
                        IconButton(onClick = { navController.navigateUp() }) {
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
            if (isShowBottomBar) {
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


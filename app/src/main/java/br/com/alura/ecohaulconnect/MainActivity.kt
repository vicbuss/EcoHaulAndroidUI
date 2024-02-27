package br.com.alura.ecohaulconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.ecohaulconnect.components.EcoHaulBottomNavBar
import br.com.alura.ecohaulconnect.components.NavBarItem
import br.com.alura.ecohaulconnect.sampledata.bottomAppBarItems
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.screens.ServiceDetailsScreen
import br.com.alura.ecohaulconnect.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.theme.EcoHaulConnectTheme
import br.com.alura.ecohaulconnect.ui.theme.White96

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination
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
                    App(
                        selectedBottomNavBarItem = selectedItem,
                        onBottomNavBarSelectedItemChange = {
                            val route = it.route
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(route)
                            }
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "services") {
                            composable("services") {
                                ServiceListScreen(
                                    services = sampleServiceList,
                                    onNavigateToServiceDetails = {navController.navigate("serviceDetails")}
                                )
                            }
                            composable("serviceDetails") {
                                ServiceDetailsScreen(service = sampleService)
                            }
                            composable("addService") {
                                Text(text = "Placeholder: Add service form")
                            }
                            composable("notifications") {
                                Text(text = "Placeholder: Notifications screen")
                            }
                        }
                        
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    selectedBottomNavBarItem: NavBarItem = bottomAppBarItems.first(),
    onBottomNavBarSelectedItemChange: (NavBarItem) -> Unit = {},
    content: @Composable () -> Unit
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                /* navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "voltar")
                    }
                } ,*/
                title = {
                    Text(
                        text = "EcoHaul Connect",
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White96,
                )
            )
        },
        bottomBar = {
            EcoHaulBottomNavBar(
                selectedItem = selectedBottomNavBarItem,
                items = bottomAppBarItems,
                onItemChange = onBottomNavBarSelectedItemChange
            )
        },
    ) {
        Box(Modifier.padding(it)) {
            content()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    EcoHaulConnectTheme {
        Surface {
            App{}
        }
    }
}




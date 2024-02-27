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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.alura.ecohaulconnect.components.EcoHaulBottomNavBar
import br.com.alura.ecohaulconnect.components.NavBarItem
import br.com.alura.ecohaulconnect.sampledata.bottomAppBarItems
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.screens.ServiceDetailsScreen
import br.com.alura.ecohaulconnect.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.theme.EcoHaulConnectTheme
import br.com.alura.ecohaulconnect.ui.theme.White96

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoHaulConnectTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = White96) {
                    App(selectedBottomNavBarItem = bottomAppBarItems.first())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(selectedBottomNavBarItem: NavBarItem) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "voltar")
                    }
                },
                title = {
                    Text(
                        text = "Meus serviços",
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
                items = bottomAppBarItems
            )
        },
    ) {
        Box(Modifier.padding(it)) {
            // ServiceListScreen(services = sampleServiceList)
            ServiceDetailsScreen()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    EcoHaulConnectTheme {
        Surface {
            App(bottomAppBarItems.first())
        }
    }
}




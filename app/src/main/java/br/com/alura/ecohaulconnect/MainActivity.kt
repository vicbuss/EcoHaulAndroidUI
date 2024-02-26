package br.com.alura.ecohaulconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.screens.ServiceListScreen
import br.com.alura.ecohaulconnect.ui.theme.EcoHaulConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
   EcoHaulConnectTheme {
       Surface {
           ServiceListScreen(sampleServiceList)
       }
   }
}




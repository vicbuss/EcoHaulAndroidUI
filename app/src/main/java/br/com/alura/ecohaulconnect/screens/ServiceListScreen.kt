package br.com.alura.ecohaulconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.components.ServiceList
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceListScreen(
    services: List<Service>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .background(White96),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier)
        ServiceList(services = services, onNavigateToServiceDetails = onNavigateToServiceDetails)
        Spacer(Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ServiceListScreenPreview() {
   ServiceListScreen(sampleServiceList)
}
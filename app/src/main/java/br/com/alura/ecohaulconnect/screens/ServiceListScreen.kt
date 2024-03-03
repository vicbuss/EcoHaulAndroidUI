package br.com.alura.ecohaulconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ecohaulconnect.components.ServiceCard
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceListScreen(
    services: List<Service>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (service: Service) -> Unit = {}
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .background(White96)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(Modifier)
        }
        // ServiceList(services = services, onNavigateToServiceDetails = onNavigateToServiceDetails)
        items(services) {service ->
            ServiceCard(service = service, onNavigateToServiceDetails = onNavigateToServiceDetails)
        }
        item {
            Spacer(Modifier)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ServiceListScreenPreview() {
   ServiceListScreen(sampleServiceList)
}
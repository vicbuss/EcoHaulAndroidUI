package br.com.alura.ecohaulconnect.ui.screens

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
import br.com.alura.ecohaulconnect.ui.components.ServiceCard
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.ui.theme.White96

data class ServiceListUiState(
    val services: List<Service> = emptyList(),
    val onNavigateToServiceDetails: (Service) -> Unit = {}
)
@Composable
fun ServiceListScreen(
    modifier: Modifier = Modifier,
    state: ServiceListUiState = ServiceListUiState()
) {
    val services = state.services
    val onNavigateToServiceDetails = state.onNavigateToServiceDetails
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
    val state = ServiceListUiState(
        services = sampleServiceList
    )
   ServiceListScreen(state = state)
}
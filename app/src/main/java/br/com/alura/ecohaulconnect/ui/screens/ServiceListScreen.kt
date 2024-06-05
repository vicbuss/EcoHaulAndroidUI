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
import br.com.alura.ecohaulconnect.navigation.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.components.ServiceCard
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.ui.components.EcoHaulScaffolding
import br.com.alura.ecohaulconnect.ui.components.NavBarItem
import br.com.alura.ecohaulconnect.ui.state.ServiceListUiState
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceListScreen(
    modifier: Modifier = Modifier,
    state: ServiceListUiState = ServiceListUiState(),
    onNavigateToServiceDetails: (Long) -> Unit = {},
    onBottomNavBarSelectedItemChange: (NavBarItem) -> Unit = {}
) {
    EcoHaulScaffolding(
        showBottomBar = state.showBottomBar,
        selectedBottomNavBarItem = state.selectedBottomNavBarItem,
        topBarTitle = state.topBarTitle,
        showNavigationIcon = state.showNavigationIcon,
        onBottomNavBarSelectedItemChange = onBottomNavBarSelectedItemChange
    ) {
        val services = state.services
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
            items(services) { service ->
                ServiceCard(service = service, onNavigateToServiceDetails = onNavigateToServiceDetails)
            }
            item {
                Spacer(Modifier)
            }
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
package br.com.alura.ecohaulconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.ui.components.ServiceDetails
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.components.EcoHaulScaffolding
import br.com.alura.ecohaulconnect.ui.state.ServiceDetailsUiState
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceDetailsScreen(
    state: ServiceDetailsUiState,
    onCancelService: (service: Service) -> Unit = {},
    onEditService: (service: Service) -> Unit = {},
    onClickArrowBack: () -> Unit = {}
    ) {
    EcoHaulScaffolding(
        onClickArrowBack = onClickArrowBack,
        topBarTitle = state.topBarTitle
    ) {
        val service = state.service
        service?.let {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(White96)) {
                Spacer(Modifier)
                ServiceDetails(service, onEditService = onEditService, onCancelService = onCancelService)
                Spacer(Modifier)
            }
        }
    }


}

@Preview
@Composable
fun ServiceDetailsScreenPreview() {
   ServiceDetailsScreen(ServiceDetailsUiState(service = sampleService))
}
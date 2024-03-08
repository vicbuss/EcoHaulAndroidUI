package br.com.alura.ecohaulconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.components.ServiceForm
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceFormScreen(
    modifier: Modifier = Modifier,
    onAddService: (service: Service) -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(White96)
    ) {
        ServiceForm(modifier, onAddService)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ServiceFormScreenPreview() {
   ServiceFormScreen()
}
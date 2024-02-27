package br.com.alura.ecohaulconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.components.ServiceDetails
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.theme.White96

@Preview(showSystemUi = true)
@Composable
fun ServiceDetailsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(White96)) {
        Spacer(Modifier)
        ServiceDetails(sampleService)
        Spacer(Modifier)
    }
}
package br.com.alura.ecohaulconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.ecohaulconnect.components.AddServiceForm
import br.com.alura.ecohaulconnect.components.EditServiceForm
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun EditServiceFormScreen(
    modifier: Modifier = Modifier,
    serviceToEdit: Service,
    onEditService: (Service) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(White96)
    ) {
        EditServiceForm(modifier, serviceToEdit, onEditService)
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditServiceFormScreenPreview() {
   EditServiceFormScreen(serviceToEdit = sampleService)
}
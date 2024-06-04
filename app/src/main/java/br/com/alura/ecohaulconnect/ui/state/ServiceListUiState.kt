package br.com.alura.ecohaulconnect.ui.state

import br.com.alura.ecohaulconnect.model.Service

data class ServiceListUiState(
    val services: List<Service> = emptyList(),
)
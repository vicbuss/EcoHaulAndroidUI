package br.com.alura.ecohaulconnect.ui.state

import br.com.alura.ecohaulconnect.model.Service

data class ServiceDetailsUiState (
    val service: Service? = null,
    val topBarTitle: String = "Detalhes"
)
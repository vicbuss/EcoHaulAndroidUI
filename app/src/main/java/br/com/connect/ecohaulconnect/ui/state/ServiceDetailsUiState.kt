package br.com.connect.ecohaulconnect.ui.state

import br.com.connect.ecohaulconnect.model.Service

data class ServiceDetailsUiState (
    val service: Service? = null,
    val topBarTitle: String = "Detalhes"
)
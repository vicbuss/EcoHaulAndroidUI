package br.com.alura.ecohaulconnect.ui.state

import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.navigation.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.components.NavBarItem

data class ServiceListUiState(
    val services: List<Service> = emptyList(),
    val showBottomBar: Boolean = true,
    val topBarTitle: String = "Serviços",
    val showNavigationIcon: Boolean = false,
    val selectedBottomNavBarItem: NavBarItem = bottomAppBarItems.first()
)
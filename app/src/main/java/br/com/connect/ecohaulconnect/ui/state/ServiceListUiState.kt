package br.com.connect.ecohaulconnect.ui.state

import br.com.connect.ecohaulconnect.model.Service
import br.com.connect.ecohaulconnect.navigation.bottomAppBarItems
import br.com.connect.ecohaulconnect.ui.components.NavBarItem

data class ServiceListUiState(
    val services: List<Service> = emptyList(),
    val showBottomBar: Boolean = true,
    val topBarTitle: String = "Serviços",
    val showNavigationIcon: Boolean = false,
    val selectedBottomNavBarItem: NavBarItem = bottomAppBarItems.first()
)
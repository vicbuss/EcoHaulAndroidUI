package br.com.alura.ecohaulconnect.ui.state

import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.navigation.bottomAppBarItems
import br.com.alura.ecohaulconnect.ui.components.NavBarItem
import java.time.LocalDate


data class ServiceFormUiState(
    var id: Long = 0L,
    var value: String = "",
    var pickedDate: LocalDate = LocalDate.now(),
    var date: String = pickedDate.toBrazilianDateFormat(),
    var description: String = "",
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var number: String = "",
    var neighborhood: String = "",
    var complement: String = "",
    var zipCode: String = "",
    var itemDescription: String = "",
    var itemCategory: String = "",
    var itemHeight: String = "",
    var itemWidth: String = "",
    var itemLength: String = "",
    var itemWeight: String = "",
    var itemImages: List<String> = listOf("", "", ""),
    var openDialog: Boolean = false,

    val onValueChange: (String) -> Unit = {},
    val onPickedDateChange: (LocalDate) -> Unit = {},
    val onDateChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onStreetChange: (String) -> Unit = {},
    val onStateChange: (String) -> Unit = {},
    val onCityChange: (String) -> Unit = {},
    val onNumberChange: (String) -> Unit = {},
    val onNeighborhoodChange: (String) -> Unit = {},
    val onComplementChange: (String) -> Unit = {},
    val onZipCodeChange: (String) -> Unit = {},
    val onItemDescriptionChange: (String) -> Unit = {},
    val onItemCategoryChange: (String) -> Unit = {},
    val onItemHeightChange: (String) -> Unit = {},
    val onItemWidthChange: (String) -> Unit = {},
    val onItemLengthChange: (String) -> Unit = {},
    val onItemWeightChange: (String) -> Unit = {},
    val onFirstImageChange: (String) -> Unit = {},
    val onSecondImageChange: (String) -> Unit = {},
    val onThirdImageChange: (String) -> Unit = {},
    val onOpenDialog: (Boolean) -> Unit = {},
    val onCloseDialog: (Boolean) -> Unit = {},

    val showBottomBar: Boolean = true,
    val selectedBottomNavBarItem: NavBarItem = bottomAppBarItems[1],
    val topBarTitle: String = "Criar um serviço"


)
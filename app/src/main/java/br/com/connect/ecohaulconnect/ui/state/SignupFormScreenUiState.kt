package br.com.connect.ecohaulconnect.ui.state

data class SignupFormScreenUiState(
    val login: String = "",
    val password: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val cpf: String = "",
    val birthDate: String = "",
    val street: String = "",
    val neighborhood: String = "",
    val zipCode: String = "",
    val city: String = "",
    val federalState: String = "",
    val complement: String = "",
    val number: String = "",
    val openDateDialog: Boolean = false,

    val onLoginChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPhoneNumberChange: (String) -> Unit = {},
    val onCpfChange: (String) -> Unit = {},
    val onBirthDateChange: (String) -> Unit = {},
    val onStreetChange: (String) -> Unit = {},
    val onNeighborhoodChange: (String) -> Unit = {},
    val onZipCodeChange: (String) -> Unit = {},
    val onCityChange: (String) -> Unit = {},
    val onFederalStateChange: (String) -> Unit = {},
    val onComplementChange: (String) -> Unit = {},
    val onNumberChange: (String) -> Unit = {},
    val onOpenDateDialog: () -> Unit = {},
    val onCloseDateDialog: () -> Unit = {}
)

package br.com.alura.ecohaulconnect.ui.state

data class LoginScreenUiState(
    val user: String = "",
    val password: String = "",
    val showError: Boolean = false,
    val onError: (Boolean) -> Unit = {},
    val onUserChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val isLoggedIn: Boolean = false
)

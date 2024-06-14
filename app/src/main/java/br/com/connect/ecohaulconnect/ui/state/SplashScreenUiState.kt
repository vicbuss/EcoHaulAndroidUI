package br.com.connect.ecohaulconnect.ui.state

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading
)

sealed class AppState {
    data object Loading: AppState()
    data object LoggedIn: AppState()
    data object  LoggedOut: AppState()
}
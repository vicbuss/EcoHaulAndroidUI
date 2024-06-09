package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateAndClear
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.LOGGEDIN
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.PWD
import br.com.alura.ecohaulconnect.preferences.PreferencesKey.USER
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.ui.screens.LoginScreen
import br.com.alura.ecohaulconnect.ui.viewModels.LoginScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.Login.route
    ) {
        val viewModel: LoginScreenViewModel = viewModel(factory = EcoHaulViewModelFactory())
        val state by viewModel.uiState.collectAsState()
        val dataStore = LocalContext.current.datastore
        val coroutineScope = rememberCoroutineScope()

        LoginScreen(
            state = state,
            onClickLogin = {
                coroutineScope.launch {
                    //isso pode ser movido para dentro do view model
                    dataStore.data.collect {preferences ->
                        val pwd = preferences[PWD]
                        val user = preferences[USER]

                        if (user == state.user && pwd == state.password) {
                            dataStore.edit {
                                it[LOGGEDIN] = true
                            }
                            viewModel.login()
                            navController.navigateAndClear(AppDestinations.SplashScreen.route)
                        } else {
                            state.onError(true)
                        }
                    }
                }
            },
            onClickSignUp = {
                navController.navigate(AppDestinations.SignupForm.route)
            }
        )
    }
}
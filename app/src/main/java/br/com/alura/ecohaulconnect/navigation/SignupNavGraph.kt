package br.com.alura.ecohaulconnect.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.ecohaulconnect.navigateAndClear
import br.com.alura.ecohaulconnect.preferences.datastore
import br.com.alura.ecohaulconnect.ui.screens.SignupFormScreen
import br.com.alura.ecohaulconnect.ui.viewModels.SignupScreenViewModel
import br.com.alura.ecohaulconnect.ui.viewModels.factory.EcoHaulViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.signupGraph(
    navController: NavHostController
) {
    composable(
        route = AppDestinations.SignupForm.route
    ) {
        val viewModel: SignupScreenViewModel = viewModel(factory = EcoHaulViewModelFactory())
        val state by viewModel.uiState.collectAsState()

        val dataStore = LocalContext.current.datastore
        val coroutineScope = rememberCoroutineScope()

        SignupFormScreen(
            state = state,
            onSave = {
                //pode ser movido para dentro do viewModel
                coroutineScope.launch {
//                    dataStore.edit { preferences ->
//                        preferences[USER] = state.login
//                        preferences[PWD] = state.password
//                    }
                }
                navController.navigateAndClear(AppDestinations.Login.route)
            }
        )
    }
}
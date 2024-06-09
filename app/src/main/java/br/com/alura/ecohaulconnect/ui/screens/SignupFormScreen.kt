package br.com.alura.ecohaulconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.ecohaulconnect.R
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.ui.state.SignupFormScreenUiState
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.IconColor
import br.com.alura.ecohaulconnect.ui.theme.SurfaceContainerHighest
import br.com.alura.ecohaulconnect.ui.theme.White96
import java.time.Instant
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupFormScreen(
    modifier: Modifier = Modifier,
    state: SignupFormScreenUiState = SignupFormScreenUiState(),
    onSave: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .background(White96)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ecohaulicon),
                contentDescription = "EcoHaulLogo",
                modifier = Modifier.size(180.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "Cadastrar",
                fontSize = 35.sp,
                fontWeight = FontWeight(400),
                color = Green40
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = IconColor,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.login,
                    onValueChange = state.onLoginChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") }
                )
                TextField(
                    value = state.password,
                    onValueChange = state.onPasswordChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Senha") }
                )
                TextField(
                    value = state.phoneNumber,
                    onValueChange = state.onPhoneNumberChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Telefone") }
                )
                TextField(
                    value = state.name,
                    onValueChange = state.onNameChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nome") }
                )
                TextField(
                    value = state.cpf,
                    onValueChange = state.onCpfChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "CPF") }
                )
                val openDialog = state.openDateDialog
                val datePickerState =
                    rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                if (openDialog) {
                    DatePickerDialog(
                        onDismissRequest = { state.onCloseDateDialog() },
                        confirmButton = {
                            TextButton(onClick = { state.onCloseDateDialog() }) {
                                Text("Ok")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { state.onCloseDateDialog() }) {
                                Text("Cancel")
                            }
                        }) {

                        DatePicker(state = datePickerState)
                    }
                }

                datePickerState.selectedDateMillis?.let {
                    val instant = Instant.ofEpochMilli(it)
                    state.onBirthDateChange(
                        instant.atOffset(ZoneOffset.UTC).toLocalDate().toBrazilianDateFormat()
                    )
                }
//                TextField(
//                    value = state.birthDate,
//                    onValueChange = state.onBirthDateChange,
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = SurfaceContainerHighest,
//                        focusedContainerColor = SurfaceContainerHighest,
//                        focusedIndicatorColor = Green40,
//                        focusedLabelColor = Green40,
//                        cursorColor = Green40,
//                        disabledContainerColor = Color(0xFFE3E3DC)
//                    ),
//                    modifier =
//                    Modifier
//                        .fillMaxWidth()
//                        .clickable { state.onOpenDateDialog() },
//                    label = { Text(text = "Data de nascimento") }
//                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = state.birthDate,
                        onValueChange = state.onBirthDateChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40,
                            disabledContainerColor = Color(0xFFE3E3DC)
                        ),
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable(enabled = false) {},
                        label = { Text(text = "Data de nascimento") }
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { state.onOpenDateDialog() }
                            .background(Color.Transparent)
                    )
                }

            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                imageVector = Icons.Filled.Place,
                contentDescription = null,
                tint = IconColor,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.zipCode,
                    onValueChange = state.onZipCodeChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "CEP") }
                )
                TextField(
                    value = state.street,
                    onValueChange = state.onStreetChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Logradouro") }
                )
                TextField(
                    value = state.neighborhood,
                    onValueChange = state.onNeighborhoodChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Bairro") }
                )
                TextField(
                    value = state.city,
                    onValueChange = state.onCityChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Cidade") }
                )
                TextField(
                    value = state.federalState,
                    onValueChange = state.onFederalStateChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "UF") }
                )
                TextField(
                    value = state.number,
                    onValueChange = state.onNumberChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Número") }
                )
                TextField(
                    value = state.complement,
                    onValueChange = state.onComplementChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Complemento") }
                )
                Button(
                    onClick = onSave,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Green40)
                ) {
                    Text(text = "Cadastrar")
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SignupFormScreenPreview() {
    SignupFormScreen()
}
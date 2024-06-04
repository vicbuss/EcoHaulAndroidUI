package br.com.alura.ecohaulconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.ui.state.ServiceFormUiState
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.IconColor
import br.com.alura.ecohaulconnect.ui.theme.SurfaceContainerHighest
import br.com.alura.ecohaulconnect.ui.theme.White96
import java.time.Instant
import java.time.ZoneOffset

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ServiceFormScreen(
    modifier: Modifier = Modifier,
    state: ServiceFormUiState = ServiceFormUiState(),
    onClickSave: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(White96)
    ) {
        val value = state.value
        val description = state.description
        val street = state.street
        val city = state.city
        val localState = state.state
        val number = state.number
        val neighborhood = state.neighborhood
        val complement = state.complement
        val zipCode = state.zipCode
        val itemDescription = state.itemDescription
        val itemCategory = state.itemCategory
        val itemHeight = state.itemHeight
        val itemWidth = state.itemWidth
        val itemLength = state.itemLength
        val itemWeight = state.itemWeight
        val images = state.itemImages
        val itemImage = images.getOrElse(0) { "" }
        val itemImage2 = images.getOrElse(1) { "" }
        val itemImage3 = images.getOrElse(2) { "" }

        Column(
            modifier
                .fillMaxSize()
                .background(White96)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null,
                    tint = IconColor
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(
                        value = description,
                        onValueChange = state.onDescriptionChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Descrição") }
                    )
                    TextField(
                        value = value,
                        onValueChange = state.onValueChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Valor") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        )
                    )
                    var openDialog = state.openDialog
                    val datePickerState =
                        rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                    if (openDialog) {
                        DatePickerDialog(
                            onDismissRequest = { state.onCloseDialog(openDialog) },
                            confirmButton = {
                                TextButton(onClick = { state.onCloseDialog(openDialog) }) {
                                    Text("Ok")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { state.onCloseDialog(openDialog) }) {
                                    Text("Cancel")
                                }
                            }) {

                            DatePicker(state = datePickerState)
                        }
                    }

                    datePickerState.selectedDateMillis?.let {
                        val instant = Instant.ofEpochMilli(it)
                        state.onPickedDateChange(instant.atOffset(ZoneOffset.UTC).toLocalDate())
                        state.onDateChange(
                            instant.atOffset(ZoneOffset.UTC).toLocalDate().toBrazilianDateFormat()
                        )
                    }
                    TextField(
                        value = state.date,
                        onValueChange = state.onDateChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40,
                            disabledContainerColor = Color(0xFFE3E3DC)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Data") },
                        enabled = false
                    )
                    Row {
                        Spacer(Modifier.weight(1f))
                        Button(onClick = { state.onOpenDialog(openDialog) }) {
                            Text(text = "Escolher Data")
                        }
                    }

                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = null,
                    tint = IconColor
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(
                        value = street,
                        onValueChange = state.onStreetChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Logradouro") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    TextField(
                        value = city,
                        onValueChange = state.onCityChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Cidade") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        TextField(
                            value = localState,
                            onValueChange = state.onStateChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(0.5f),
                            label = { Text("UF") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        TextField(
                            value = number,
                            onValueChange = state.onNumberChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(1f),
                            label = { Text("Número") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                    TextField(
                        value = neighborhood,
                        onValueChange = state.onNeighborhoodChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Bairro") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextField(
                            value = complement,
                            onValueChange = state.onComplementChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(0.5f),
                            label = { Text("Complemento") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                        TextField(
                            value = zipCode,
                            onValueChange = state.onZipCodeChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(1f),
                            label = { Text("Cep") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = null,
                    tint = IconColor
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    TextField(
                        value = itemDescription,
                        onValueChange = state.onItemDescriptionChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Descrição do item") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    TextField(
                        value = itemCategory,
                        onValueChange = state.onItemCategoryChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Tipo") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextField(
                            value = itemHeight,
                            onValueChange = state.onItemHeightChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(0.5f),
                            label = { Text("Altura") },
                            placeholder = { Text(text = "Altura em centímetros") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )

                        TextField(
                            value = itemWidth,
                            onValueChange = state.onItemWidthChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(1f),
                            label = { Text("Largura") },
                            placeholder = { Text(text = "Largura em centímetros") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        TextField(
                            value = itemLength,
                            onValueChange = state.onItemLengthChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(0.5f),
                            label = { Text("Comprimento") },
                            placeholder = { Text(text = "Comprimento em centímetros") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )

                        TextField(
                            value = itemWeight,
                            onValueChange = state.onItemWeightChange,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedContainerColor = SurfaceContainerHighest,
                                focusedIndicatorColor = Green40,
                                focusedLabelColor = Green40,
                                cursorColor = Green40
                            ),
                            modifier = Modifier.fillMaxWidth(1f),
                            label = { Text("Peso") },
                            placeholder = { Text(text = "Peso em kg") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                    TextField(
                        value = itemImage,
                        onValueChange = state.onFirstImageChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Url da imagem") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    TextField(
                        value = itemImage2,
                        onValueChange = state.onSecondImageChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Url da imagem") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    TextField(
                        value = itemImage3,
                        onValueChange = state.onThirdImageChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Url da imagem") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                }
            }


            Row {
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { onClickSave() },
                    colors = ButtonDefaults.buttonColors(containerColor = Green40)
                ) {
                    val buttonText = "Salvar"
                    Text(text = buttonText)
                }
            }
            Spacer(Modifier)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditServiceFormScreenPreview() {
    ServiceFormScreen()
}
package br.com.alura.ecohaulconnect.components

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.model.Address
import br.com.alura.ecohaulconnect.model.Item
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.IconColor
import br.com.alura.ecohaulconnect.ui.theme.SurfaceContainerHighest
import br.com.alura.ecohaulconnect.ui.theme.White96
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceForm(
    modifier: Modifier = Modifier,
    onAddService: (service: Service) -> Unit = {}
) {
    var value by remember {
        mutableStateOf("")
    }
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var date by remember {
        mutableStateOf(pickedDate.toBrazilianDateFormat())
    }
    var description by remember {
        mutableStateOf("")
    }
    var street by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var state by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var neighborhood by remember {
        mutableStateOf("")
    }
    var complement by remember {
        mutableStateOf("")
    }
    var zipCode by remember {
        mutableStateOf("")
    }
    var itemQty by remember {
        mutableStateOf("1")
    }
    var itemDescription by remember {
        mutableStateOf("")
    }
    var itemCategory by remember {
        mutableStateOf("")
    }
    var itemHeight by remember {
        mutableStateOf("")
    }
    var itemWidth by remember {
        mutableStateOf("")
    }
    var itemLength by remember {
        mutableStateOf("")
    }
    var itemWeight by remember {
        mutableStateOf("")
    }
    var itemImage by remember {
        mutableStateOf("")
    }
    var itemImage2 by remember {
        mutableStateOf("")
    }
    var itemImage3 by remember {
        mutableStateOf("")
    }

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
                    value = value,
                    onValueChange = { value = it },
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
                var openDialog by remember { mutableStateOf(false) }
                val datePickerState =
                    rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                if (openDialog) {
                    DatePickerDialog(
                        onDismissRequest = { openDialog = false },
                        confirmButton = {
                            TextButton(onClick = { openDialog = false }) {
                                Text("Ok")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { openDialog = false }) {
                                Text("Cancel")
                            }
                        }) {

                        DatePicker(state = datePickerState)
                    }
                }

                datePickerState.selectedDateMillis?.let {
                    val instant = Instant.ofEpochMilli(it)
                    pickedDate = instant.atOffset(ZoneOffset.UTC).toLocalDate()
                    date = pickedDate.toBrazilianDateFormat()
                }



                TextField(
                    value = description,
                    onValueChange = { description = it },
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
                    value = date,
                    onValueChange = { date = it },
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
                    Button(onClick = { openDialog = true }) {
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
                    onValueChange = { street = it },
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
                    onValueChange = { city = it },
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
                        value = state,
                        onValueChange = { state = it },
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
                        onValueChange = { number = it },
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
                    onValueChange = { neighborhood = it },
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
                        onValueChange = { complement = it },
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
                        onValueChange = { zipCode = it },
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
                    onValueChange = { itemDescription = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceContainerHighest,
                        focusedContainerColor = SurfaceContainerHighest,
                        focusedIndicatorColor = Green40,
                        focusedLabelColor = Green40,
                        cursorColor = Green40
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Item") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                TextField(
                    value = itemCategory,
                    onValueChange = { itemCategory = it },
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
                        onValueChange = { itemHeight = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        label = { Text("Altura") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    TextField(
                        value = itemWidth,
                        onValueChange = { itemWidth = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(1f),
                        label = { Text("Largura") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    TextField(
                        value = itemLength,
                        onValueChange = { itemLength = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        label = { Text("Comprimento") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    TextField(
                        value = itemWeight,
                        onValueChange = { itemWeight = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedContainerColor = SurfaceContainerHighest,
                            focusedIndicatorColor = Green40,
                            focusedLabelColor = Green40,
                            cursorColor = Green40
                        ),
                        modifier = Modifier.fillMaxWidth(1f),
                        label = { Text("Peso") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                TextField(
                    value = itemImage,
                    onValueChange = { itemImage = it },
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
                    onValueChange = { itemImage2 = it },
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
                    onValueChange = { itemImage3 = it },
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
                onClick = {
                    val address = Address(
                        street = street,
                        city = city,
                        state = state,
                        number = number,
                        neighborhood = neighborhood,
                        complement = complement,
                        zipCode = zipCode
                    )
                    val itemList = listOf(
                        Item(
                            pictureLinks = listOf(itemImage, itemImage2, itemImage3),
                            description = itemDescription,
                            heightInCm = itemHeight.toInt(),
                            widthInCm = itemWidth.toInt(),
                            lengthInCm = itemLength.toInt(),
                            weightInKilograms = itemWeight.toInt()
                        )
                    )
                    val service = Service(
                        category = itemCategory,
                        date = LocalDate.parse(date, dateFormatter),
                        description = description,
                        value = BigDecimal(value),
                        items = itemList,
                        address = address,
                        status = "ativo"
                    )
                    onAddService(service)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green40)
            ) {
                Text(text = "Adicionar")
            }
        }
        Spacer(Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceFormPreview() {
    ServiceForm()
}
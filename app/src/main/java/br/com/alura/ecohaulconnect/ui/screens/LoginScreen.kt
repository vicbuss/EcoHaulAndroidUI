package br.com.alura.ecohaulconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.ecohaulconnect.R
import br.com.alura.ecohaulconnect.ui.state.LoginScreenUiState
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.IconColor
import br.com.alura.ecohaulconnect.ui.theme.SurfaceContainerHighest
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginScreenUiState = LoginScreenUiState(),
    onClickLogin: () -> Unit = {},
    onClickSignUp: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .background(White96)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ecohaulicon),
                contentDescription = "EcoHaulLogo",
                modifier = Modifier.size(180.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "EcoHaul Connect",
                fontSize = 35.sp,
                fontWeight = FontWeight(400),
                color = Green40
            )
        }
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .weight(2f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (state.showError) {
                Text(
                    text = "Usuário ou senha incorretos",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            }
            OutlinedTextField(
                value = state.user,
                onValueChange = state.onUserChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Usuário")},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = IconColor
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = SurfaceContainerHighest,
                    focusedContainerColor = SurfaceContainerHighest,
                    focusedBorderColor = Green40,
                    focusedLabelColor = Green40,
                    cursorColor = Green40
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = state.onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Senha")},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = IconColor
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = SurfaceContainerHighest,
                    focusedContainerColor = SurfaceContainerHighest,
                    focusedBorderColor = Green40,
                    focusedLabelColor = Green40,
                    cursorColor = Green40
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onClickLogin,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Green40)
            ) {
                Text(text = "Login")
            }
            TextButton(
                onClick = onClickSignUp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Criar conta",
                    color = Green40
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

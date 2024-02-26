package br.com.alura.ecohaulconnect.sampledata

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import br.com.alura.ecohaulconnect.components.NavBarItem
import br.com.alura.ecohaulconnect.model.Service
import java.math.BigDecimal
import java.time.LocalDate

val sampleServiceList = listOf<Service>(
    Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19")
    ), Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19")
    ), Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19")
    ), Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19")
    ), Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19")
    )
)

val sampleService = Service(
    descricao = "Um sofá de dois lugares para descartar",
    valor = BigDecimal("250.00"),
    status = "ativo",
    data = LocalDate.parse("2023-10-19")
)

val bottomAppBarItems = listOf<NavBarItem>(
    NavBarItem(
        label = "Meus serviços",
        icon = Icons.Filled.Home
    ),
    NavBarItem(
        label = "Perfil",
        icon = Icons.Filled.AccountCircle
    ),
    NavBarItem(
        label = "Notificações",
        icon = Icons.Filled.Notifications
    )
)
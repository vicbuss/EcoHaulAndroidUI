package br.com.alura.ecohaulconnect.sampledata

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import br.com.alura.ecohaulconnect.components.NavBarItem
import br.com.alura.ecohaulconnect.model.Item
import br.com.alura.ecohaulconnect.model.Service
import java.math.BigDecimal
import java.time.LocalDate

val sampleServiceList = listOf<Service>(
    Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19"),
        address = "Praça da Sé, 2, Ap 105. Sé, São Paulo, SP",
        items = listOf(
            Item(
                pictureLinks = listOf(
                    "https://images.pexels.com/photos/1866149/pexels-photo-1866149.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/1248583/pexels-photo-1248583.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/1571471/pexels-photo-1571471.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                description = "Um sofá de dois lugares",
                heightInCm = 50,
                widthInCm = 50,
                lengthInCm = 120,
                weightInGrams = 5000

            )
        ),
        category = "Móveis"
    ), Service(
        descricao = "Um fogão para doar",
        valor = BigDecimal("50"),
        status = "concluído",
        data = LocalDate.parse("2023-05-01"),
        address = "Praça da Sé, 2, Ap 105. Sé, São Paulo, SP",
        items = listOf(
            Item(
                pictureLinks = listOf(
                    "https://images.pexels.com/photos/2062426/pexels-photo-2062426.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/4099350/pexels-photo-4099350.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                description = "Um fogão para doar",
                heightInCm = 50,
                widthInCm = 50,
                lengthInCm = 120,
                weightInGrams = 10000

            )
        ),
        category = "Móveis"
    ), Service(
        descricao = "Uma caixa de livros para doar",
        valor = BigDecimal("50.00"),
        status = "concluído",
        data = LocalDate.parse("2023-03-10"),
        address = "Praça da Sé, 2, Ap 105. Sé, São Paulo, SP",
        items = listOf(
            Item(
                pictureLinks = listOf(
                    "https://images.pexels.com/photos/4498124/pexels-photo-4498124.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/1148399/pexels-photo-1148399.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1jh",
                    "https://images.pexels.com/photos/694740/pexels-photo-694740.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                description = "Um sofá de dois lugares",
                heightInCm = 50,
                widthInCm = 50,
                lengthInCm = 120,
                weightInGrams = 5000

            )
        ),
        category = "Móveis"
    )
)

val sampleService = Service(
        descricao = "Um sofá de dois lugares para descartar",
        valor = BigDecimal("250.00"),
        status = "ativo",
        data = LocalDate.parse("2023-10-19"),
        address = "Praça da Sé, 2, Ap 105. Sé, São Paulo, SP",
        items = listOf(
            Item(
                pictureLinks = listOf(
                    "https://images.pexels.com/photos/1866149/pexels-photo-1866149.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/1248583/pexels-photo-1248583.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "https://images.pexels.com/photos/1571471/pexels-photo-1571471.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                description = "Um sofá de dois lugares",
                heightInCm = 50,
                widthInCm = 50,
                lengthInCm = 120,
                weightInGrams = 5000

            )
        ),
        category = "Móveis"
)

val bottomAppBarItems = listOf<NavBarItem>(
    NavBarItem(
        label = "Meus serviços",
        icon = Icons.Filled.Home,
        route = "services"
    ),
    NavBarItem(
        label = "Novo serviço",
        icon = Icons.Filled.AddCircle,
        route = "addService"
    ),
    NavBarItem(
        label = "Notificações",
        icon = Icons.Filled.Notifications,
        route = "notifications"
    )
)
package br.com.connect.ecohaulconnect.sampledata

import br.com.connect.ecohaulconnect.model.Address
import br.com.connect.ecohaulconnect.model.Item
import br.com.connect.ecohaulconnect.model.Service
import java.math.BigDecimal
import java.time.LocalDate

val sampleAdress = Address(
    street = "Praça da Sé",
    neighborhood = "Sé",
    zipCode = "01001-000",
    city = "São Paulo",
    state = "SP",
    number = "2",
    complement = "Ap 105"
)

val sampleServiceList = listOf(
    Service(
        id = 1L,
        description = "Um sofá de dois lugares para descartar",
        value = BigDecimal("250.00"),
        status = "ativo",
        date = LocalDate.parse("2023-10-19"),
        address = sampleAdress,
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
                weightInKilograms = 5000

            )
        ),
        category = "Móveis"
    ), Service(
        id = 2L,
        description = "Um fogão para doar",
        value = BigDecimal("50"),
        status = "concluído",
        date = LocalDate.parse("2023-05-01"),
        address = sampleAdress,
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
                weightInKilograms = 10000

            )
        ),
        category = "Móveis"
    ), Service(
        id = 3L,
        description = "Uma caixa de livros para doar",
        value = BigDecimal("50.00"),
        status = "concluído",
        date = LocalDate.parse("2023-03-10"),
        address = sampleAdress,
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
                weightInKilograms = 5000

            )
        ),
        category = "Móveis"
    )
)

val sampleService = Service(
        id = 1L,
        description = "Um sofá de dois lugares para descartar",
        value = BigDecimal("250.00"),
        status = "ativo",
        date = LocalDate.parse("2023-10-19"),
        address = sampleAdress,
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
                weightInKilograms = 5000

            )
        ),
        category = "Móveis"
)

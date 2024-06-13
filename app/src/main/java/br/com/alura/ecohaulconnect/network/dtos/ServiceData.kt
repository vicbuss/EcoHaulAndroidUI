package br.com.alura.ecohaulconnect.network.dtos

import br.com.alura.ecohaulconnect.extensions.fromUtcStringToLocalDate
import br.com.alura.ecohaulconnect.model.Address
import br.com.alura.ecohaulconnect.model.Item
import br.com.alura.ecohaulconnect.model.Service
import java.math.BigDecimal

data class ServiceListResponse(
    val content: List<ServiceData>
)

data class ServiceData(
    val id: Long,
    val idCliente: Long,
    val valor: BigDecimal,
    val dataAgendamento: String,
    val descricao: String,
    val endereco: AddressData,
    val itens: List<ItemData>,
    val ativo: Boolean
)

data class NewServiceData(
    val idCliente: Long,
    val valor: BigDecimal,
    val dataAgendamento: String,
    val descricao: String,
    val endereco: AddressData,
    val itens: List<ItemData>
)

data class ItemData(
    val tipo: String,
    val alturaEmCm: Int,
    val larguraEmCm: Int,
    val comprimentoEmCm: Int,
    val pesoEmGramas: Int,
    val descricao: String,
    val imagens: List<ImageData>
)

data class ImageData (
    val url: String
)

fun ServiceData.toService(): Service {
    return Service (
        id = id,
        description = descricao,
        status = if (ativo) "ativo" else "cancelado",
        value = valor,
        date = dataAgendamento.fromUtcStringToLocalDate(),
        address = endereco.toAddress(),
        category = itens[0].tipo.lowercase(),
        items = itens.map { item -> item.toItem() }
    )
}

fun ItemData.toItem(): Item {
    return Item(
        pictureLinks = imagens.map { imageData ->  imageData.toLinks()},
        description = descricao,
        heightInCm = alturaEmCm,
        widthInCm = larguraEmCm,
        lengthInCm = comprimentoEmCm,
        weightInKilograms = pesoEmGramas / 1000,
    )
}

fun ImageData.toLinks(): String {
    return url
}

fun AddressData.toAddress(): Address {
    return Address(
        street = logradouro,
        neighborhood = bairro,
        zipCode = cep,
        city = cidade,
        state = uf,
        number = numero,
        complement = complemento
    )
}




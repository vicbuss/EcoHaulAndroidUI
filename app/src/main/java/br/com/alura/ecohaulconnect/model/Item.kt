package br.com.alura.ecohaulconnect.model

import br.com.alura.ecohaulconnect.network.dtos.ImageData
import br.com.alura.ecohaulconnect.network.dtos.ItemData

data class Item(
    val pictureLinks: List<String>,
    val description: String,
    val heightInCm: Int,
    val widthInCm: Int,
    val lengthInCm: Int,
    val weightInKilograms: Int
)

fun Item.toItemData(type: String): ItemData {
    return ItemData(
        descricao = description,
        alturaEmCm = heightInCm,
        comprimentoEmCm = lengthInCm,
        larguraEmCm = widthInCm,
        pesoEmGramas = weightInKilograms * 1000,
        tipo = type,
        imagens = pictureLinks.map { link -> pictureLinkToImageData(link) }
    )
}

fun pictureLinkToImageData(link: String): ImageData {
    return ImageData(url = link)
}

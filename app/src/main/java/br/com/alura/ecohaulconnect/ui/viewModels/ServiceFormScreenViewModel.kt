package br.com.alura.ecohaulconnect.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.alura.ecohaulconnect.data.ServiceDao
import br.com.alura.ecohaulconnect.extensions.toBrazilianCurrency
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.model.Address
import br.com.alura.ecohaulconnect.model.Item
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.ui.state.ServiceFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ServiceFormScreenViewModel(private val serviceId: Long) : ViewModel() {
    private val dao = ServiceDao()
    private var persistenceStrategy = PersistenceStrategy.INSERT
    private val _uiState: MutableStateFlow<ServiceFormUiState> =
        MutableStateFlow(ServiceFormUiState())

    val uiState get() = _uiState

    init {
        loadService(serviceId)
        _uiState.update { state ->
            state.copy(
                onValueChange = {
                    _uiState.value = _uiState.value.copy(
                        value = it
                    )
                },
                onPickedDateChange = {
                    _uiState.value = _uiState.value.copy(
                        pickedDate = it
                    )
                },
                onDateChange = {
                    _uiState.value = _uiState.value.copy(
                        date = it
                    )
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                },
                onStreetChange = {
                    _uiState.value = _uiState.value.copy(
                        street = it
                    )
                },
                onStateChange = {
                    _uiState.value = _uiState.value.copy(
                        state = it
                    )
                },
                onCityChange = {
                    _uiState.value = _uiState.value.copy(
                        city = it
                    )
                },
                onNumberChange = {
                    _uiState.value = _uiState.value.copy(
                        number = it
                    )
                },
                onNeighborhoodChange = {
                    _uiState.value = _uiState.value.copy(
                        neighborhood = it
                    )
                },
                onComplementChange = {
                    _uiState.value = _uiState.value.copy(
                        complement = it
                    )
                },
                onZipCodeChange = {
                    _uiState.value = _uiState.value.copy(
                        zipCode = it
                    )
                },
                onItemDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        itemDescription = it
                    )
                },
                onItemCategoryChange = {
                    _uiState.value = _uiState.value.copy(
                        itemCategory = it
                    )
                },
                onItemHeightChange = {
                    _uiState.value = _uiState.value.copy(
                        itemHeight = it
                    )
                },
                onItemWidthChange = {
                    _uiState.value = _uiState.value.copy(
                        itemWidth = it
                    )
                },
                onItemLengthChange = {
                    _uiState.value = _uiState.value.copy(
                        itemLength = it
                    )
                },
                onItemWeightChange = {
                    _uiState.value = _uiState.value.copy(
                        itemWeight = it
                    )
                },
                onFirstImageChange = {
                    val newItemList = _uiState.value.itemImages.toMutableList()
                    newItemList[0] = it
                    _uiState.value = _uiState.value.copy(
                        itemImages = newItemList.toList()
                    )
                },
                onSecondImageChange = {
                    val newItemList = _uiState.value.itemImages.toMutableList()
                    newItemList[1] = it
                    _uiState.value = _uiState.value.copy(
                        itemImages = newItemList.toList()
                    )
                },
                onThirdImageChange = {
                    val newItemList = _uiState.value.itemImages.toMutableList()
                    newItemList[2] = it
                    _uiState.value = _uiState.value.copy(
                        itemImages = newItemList.toList()
                    )
                },
                onOpenDialog = {
                    _uiState.value = _uiState.value.copy(
                        openDialog = true
                    )
                },
                onCloseDialog = {
                    _uiState.value = _uiState.value.copy(
                        openDialog = false
                    )
                }
            )

        }
    }

    private fun loadService(id: Long) {
        val service = dao.getServiceById(id)
        service?.let {
            this.persistenceStrategy = PersistenceStrategy.UPDATE
            with(it) {
                _uiState.value = _uiState.value.copy(
                    value = value.toBrazilianCurrency(),
                    pickedDate = date,
                    date = date.toBrazilianDateFormat(),
                    description = description,
                    street = address.street,
                    city = address.city,
                    state = address.state,
                    number = address.number,
                    neighborhood = address.neighborhood,
                    complement = address.complement,
                    zipCode = address.zipCode,
                    itemDescription = items[0].description,
                    itemCategory = category,
                    itemHeight = items[0].heightInCm.toString(),
                    itemWidth = items[0].widthInCm.toString(),
                    itemLength = items[0].lengthInCm.toString(),
                    itemWeight = items[0].weightInKilograms.toString(),
                    itemImages = populateItemListForDisplay(items[0].pictureLinks)
                )
            }
        }
    }

    private fun populateItemListForDisplay(itemImages: List<String>): List<String> {
        return if (itemImages.size == 3) {
            itemImages
        } else {
            val populatedList = mutableListOf("", "", "")
            for (i in itemImages.indices) {
                populatedList[i] = itemImages[i]
            }
            populatedList.toList()
        }
    }

    fun createOrEditService() {
        _uiState.value.run {
            val address = Address(
                street = street,
                city = city,
                zipCode = zipCode,
                complement = complement,
                neighborhood = neighborhood,
                number = number,
                state = state
            )
            val pictureLinks = mutableListOf<String>()
            itemImages.forEach { imageLink -> if (imageLink != "") pictureLinks.add(imageLink) }
            val itemList = listOf(
                Item(
                    pictureLinks = pictureLinks,
                    description = itemDescription,
                    weightInKilograms = itemWeight.toInt(),
                    lengthInCm = itemLength.toInt(),
                    widthInCm = itemWidth.toInt(),
                    heightInCm = itemHeight.toInt(),
                )
            )
            val formattedValue = value.replace("R$", "").replace(",", ".").trim()
            Log.i("ServiceFormViewModel", "createOrEditService: $formattedValue")
            val service = Service(
                id = serviceId,
                category = itemCategory,
                description = description,
                // alterar para pegar do form
                status = "ativo",
                value = BigDecimal(formattedValue),
                date = parseStringToDate(date),
                address = address,
                items = itemList
            )

            if (persistenceStrategy == PersistenceStrategy.INSERT) {
                dao.addService(service)
            } else {
                dao.editService(service)
            }
        }
    }

    private fun parseStringToDate(dateString: String): LocalDate {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(dateString, dateFormatter)
    }
}

enum class PersistenceStrategy {
    INSERT,
    UPDATE
}
package br.com.alura.ecohaulconnect.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import br.com.alura.ecohaulconnect.R
import br.com.alura.ecohaulconnect.extensions.toBrazilianCurrency
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.theme.FontColor
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.White96
import coil.compose.AsyncImage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServiceDetails(
    service: Service,
    modifier: Modifier = Modifier,
    onEditService: () -> Unit = {},
    onCancelService: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxHeight()
            .background(White96)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Status",
                color = FontColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400)
            )
            Text(
                text = service.status,
                color = FontColor,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
        Row(Modifier.padding(bottom = 16.dp)) {
            Column {
                Text(
                    text = "Valor",
                    color = FontColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
                Text(
                    text = service.value.toBrazilianCurrency(),
                    color = FontColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    text = "Data",
                    color = FontColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
                Text(
                    text = service.date.toBrazilianDateFormat(),
                    color = FontColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
        }
        Column(Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Endereço",
                color = FontColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400)
            )
            Text(
                text = "${service.address.street}, ${service.address.number}, ${service.address.complement}. ${service.address.neighborhood}, ${service.address.city}, ${service.address.state}",
                color = FontColor,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
        Divider()
        Column {
            for (item in service.items) {
                Column(Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                    Text(
                        text = "Item ${service.items.indexOf(item) + 1}",
                        color = FontColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400)
                    )
                    Text(
                        text = "${service.category} - ${item.description}",
                        color = FontColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                }
                val pictures = item.pictureLinks
                val pagerState = rememberPagerState(pageCount = {Int.MAX_VALUE})
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(200.dp)
                ) { page ->
                    var quotient by remember {
                        mutableIntStateOf(page / pictures.size)
                    }
                    var listIndex by remember {
                       mutableIntStateOf(page - pictures.size * quotient)
                    }

                    quotient = page / pictures.size
                    listIndex = page - pictures.size * quotient

                    Card(
                        Modifier
                            .size(200.dp)
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue
                                lerp(
                                    start = 0.75f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )

                            }) {
                        AsyncImage(
                            model = pictures[listIndex],
                            contentDescription = null,
                            Modifier
                                .size(200.dp),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.ic_launcher_background)
                        )
                    }
                }
                Row(Modifier.padding(vertical = 16.dp)) {
                    Column {
                        Column(Modifier.padding(bottom = 16.dp)) {
                            Text(
                                text = "Altura",
                                color = FontColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                            Text(
                                text = "50 cm",
                                color = FontColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                        Column {
                            Text(
                                text = "Comprimento",
                                color = FontColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                            Text(
                                text = "120 cm",
                                color = FontColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        Column(Modifier.padding(bottom = 16.dp)) {
                            Text(
                                text = "Largura",
                                color = FontColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                            Text(
                                text = "50 cm",
                                color = FontColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )
                        }
                        Column {
                            Text(
                                text = "Peso",
                                color = FontColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                            Text(
                                text = "5 kg",
                                color = FontColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.padding(bottom = 16.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {onEditService()},
                Modifier
                    .padding(end = 8.dp)
                    .border(
                        width = 1.dp,
                        color = Green40,
                        shape = RoundedCornerShape(50)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White96,
                    contentColor = Green40
                )
            ) {
                Text(text = "Editar")
            }
            Button(
                onClick = {onCancelService()},
                colors = ButtonDefaults.buttonColors(containerColor = Green40)
            ) {
                Text(text = "Cancelar serviço")
            }
        }
    }
}

@Preview
@Composable
fun ServiceDetailsPreview() {
    ServiceDetails(service = sampleService)

}
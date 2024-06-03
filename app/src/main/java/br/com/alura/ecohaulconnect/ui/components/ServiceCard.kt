package br.com.alura.ecohaulconnect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.ecohaulconnect.extensions.toBrazilianCurrency
import br.com.alura.ecohaulconnect.extensions.toBrazilianDateFormat
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleService
import br.com.alura.ecohaulconnect.ui.theme.FontColor
import br.com.alura.ecohaulconnect.ui.theme.Green40
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceCard(
    service: Service,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (service: Service) -> Unit = {},
) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier
                .width(380.dp)
                .height(240.dp)
                .background(White96)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = service.description,
                    color = FontColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Status: ${service.status}",
                    modifier = Modifier.padding(top = 4.dp),
                    color = FontColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            Divider(Modifier.padding(horizontal = 16.dp))
            Row(Modifier.padding(16.dp)) {
                Column {
                    Text(
                        text = "Valor",
                        color = FontColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400)
                    )
                    Text(
                        text = service.value.toBrazilianCurrency(),
                        modifier = Modifier.padding(top = 4.dp),
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
                        modifier = Modifier.padding(top = 4.dp),
                        color = FontColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }
            Row(Modifier.padding(top = 32.dp, bottom = 16.dp, end = 16.dp, start = 16.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {onNavigateToServiceDetails(service)},
                    colors = ButtonDefaults.buttonColors(containerColor = Green40)
                ) {
                    Text(text = "Detalhes")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceCardPreview() {
    ServiceCard(sampleService)
}
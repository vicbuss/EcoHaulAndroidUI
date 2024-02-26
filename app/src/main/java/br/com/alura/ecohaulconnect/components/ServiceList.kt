package br.com.alura.ecohaulconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.ecohaulconnect.model.Service
import br.com.alura.ecohaulconnect.sampledata.sampleServiceList
import br.com.alura.ecohaulconnect.ui.theme.White96

@Composable
fun ServiceList(services: List<Service>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier
            .background(White96)
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /* item() {
            Row(Modifier.padding(top = 16.dp)) {
                Text(
                    text = "Meus serviços",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(400)
                )
            }
        } */
       item {
           Spacer(modifier = Modifier)
       }

        items(services) {service ->
            ServiceCard(service = service)
        }

        item {
            Spacer(modifier = Modifier)
        }
    }
}



@Preview
@Composable
fun ServiceListPreview() {
    ServiceList(sampleServiceList)
}
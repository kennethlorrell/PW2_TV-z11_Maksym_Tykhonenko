package com.deepdark.lab2.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun EmissionCalculatorPagePreview() {
    EmissionCalculatorPage()
}

@Composable
fun EmissionCalculatorPage() {
    var coalQuantity by remember { mutableStateOf("") }
    var fuelOilQuantity by remember { mutableStateOf("") }
    var gasQuantity by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть обсяг палива:")

        OutlinedTextField(
            value = coalQuantity,
            onValueChange = { coalQuantity = it },
            label = { Text("Вугілля (т)") }
        )

        OutlinedTextField(
            value = fuelOilQuantity,
            onValueChange = { fuelOilQuantity = it },
            label = { Text("Мазут (т)") }
        )

        OutlinedTextField(
            value = gasQuantity,
            onValueChange = { gasQuantity = it },
            label = { Text("Природний газ (тис. м³)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

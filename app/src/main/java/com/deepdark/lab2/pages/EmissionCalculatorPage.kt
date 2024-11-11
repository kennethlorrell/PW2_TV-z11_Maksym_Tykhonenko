package com.deepdark.lab2.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepdark.lab2.data.EmissionResult
import com.deepdark.lab2.data.calculateEmissions
import com.deepdark.lab2.utils.roundTo

@Preview(showBackground = true)
@Composable
fun EmissionCalculatorPagePreview() {
    EmissionCalculatorPage()
}

@Composable
fun EmissionCalculatorPage() {
    var coalQuantity by remember { mutableStateOf("1096363") }
    var fuelOilQuantity by remember { mutableStateOf("70945") }
    var gasQuantity by remember { mutableStateOf("84762") }

    var results by remember { mutableStateOf<Map<String, EmissionResult>?>(null) }

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
            results = calculateEmissions(
                coalQuantity.toDoubleOrNull() ?: 0.0,
                fuelOilQuantity.toDoubleOrNull() ?: 0.0,
                gasQuantity.toDoubleOrNull() ?: 0.0
            )
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        results?.let {
            it.forEach { (fuelType, emissionResult) ->
                Text("Викиди твердих частинок $fuelType: ${emissionResult.solidParticleEmissions.roundTo(2)} г/ГДж")
                Text("Валовий викид $fuelType: ${emissionResult.grossEmissions.roundTo(2)} т")
            }
        }
    }
}

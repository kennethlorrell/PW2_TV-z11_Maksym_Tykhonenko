package com.deepdark.lab2.data

import com.deepdark.lab2.utils.COAL_HEATING_VALUE
import com.deepdark.lab2.utils.FUEL_OIL_HEATING_VALUE
import com.deepdark.lab2.utils.GAS_HEATING_VALUE
import com.deepdark.lab2.utils.COAL_EMISSION_FACTOR
import com.deepdark.lab2.utils.FUEL_OIL_EMISSION_FACTOR
import com.deepdark.lab2.utils.GAS_EMISSION_FACTOR

fun calculateEmissions(
    coalQuantity: Double,
    fuelOilQuantity: Double,
    gasQuantity: Double
): Map<String, EmissionResult> {
    return mapOf(
        "вугілля" to calculateEmissionForFuel(
            quantity = coalQuantity,
            emissionFactor = COAL_EMISSION_FACTOR,
            heatingValue = COAL_HEATING_VALUE
        ),
        "мазута" to calculateEmissionForFuel(
            quantity = fuelOilQuantity,
            emissionFactor = FUEL_OIL_EMISSION_FACTOR,
            heatingValue = FUEL_OIL_HEATING_VALUE
        ),
        "газу" to calculateEmissionForFuel(
            quantity = gasQuantity,
            emissionFactor = GAS_EMISSION_FACTOR,
            heatingValue = GAS_HEATING_VALUE
        )
    )
}

fun calculateEmissionForFuel(quantity: Double, emissionFactor: Double, heatingValue: Double): EmissionResult {
    val totalEmissions = 1e-6 * emissionFactor * quantity * heatingValue

    return EmissionResult(emissionFactor, totalEmissions)
}

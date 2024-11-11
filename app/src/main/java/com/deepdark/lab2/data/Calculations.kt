package com.deepdark.lab2.data

import com.deepdark.lab2.utils.*

// Розрахуємо показник емісії твердих частинок при спалюванні палива
fun calculateSolidParticleEmissions(
    lowerHeatingValue: Double,
    ash: Double,
    lightAsh: Double,
    componentPercentageG: Double,
    particulateCaptureEfficiency: Double
): Double {
    return (1e6 / lowerHeatingValue) * ash * (lightAsh / (100 - componentPercentageG)) * (1 - particulateCaptureEfficiency)
}

// Отримуємо показник емісії та валовий викид твердих частинок для вугілля
fun calculateCoalEmission(fuelQuantity: Double): EmissionResult {
    val solidParticleEmissions = calculateSolidParticleEmissions(
        lowerHeatingValue = COAL_LOWER_HEATING_VALUE,
        ash = COAL_LIGHT_ASH_CONTENT,
        lightAsh = COAL_ASH_MASS_CONTENT,
        componentPercentageG = COAL_COMBUSTIBLE_MASS_CONTENT,
        particulateCaptureEfficiency = COAL_CLEANING_EFFICIENCY
    )

    val grossEmissions = calculateGrossEmissions(
        solidParticleEmissions,
        COAL_LOWER_HEATING_VALUE,
        fuelQuantity
    )

    return EmissionResult(solidParticleEmissions, grossEmissions)
}

// Отримуємо показник емісії та валовий викид твердих частинок для мазуту
fun calculateFuelOilEmission(fuelQuantity: Double): EmissionResult {
    val solidParticleEmissions = calculateSolidParticleEmissions(
        lowerHeatingValue = FUEL_OIL_LOWER_HEATING_VALUE,
        ash = FUEL_OIL_LIGHT_ASH_CONTENT,
        lightAsh = FUEL_ASH_MASS_CONTENT,
        componentPercentageG = FUEL_OIL_COMPONENT_PERCENTAGE_G,
        particulateCaptureEfficiency = FUEL_OIL_CLEANING_EFFICIENCY
    )

    val grossEmission = calculateGrossEmissions(
        solidParticleEmissions,
        FUEL_OIL_LOWER_HEATING_VALUE,
        fuelQuantity
    )

    return EmissionResult(solidParticleEmissions, grossEmission)
}

// Отримуємо показник емісії та валовий викид твердих частинок для природнього газу
fun calculateGasEmission(fuelQuantity: Double): EmissionResult {
    return EmissionResult(0.0, 0.0)
}

// Розраховуємо валовий викид твердих частинок при спалюванні палива
fun calculateGrossEmissions(
    solidParticleEmissions: Double,
    lowerHeatingValue: Double,
    fuelQuantity: Double
): Double {
    return 1e-6 * solidParticleEmissions * lowerHeatingValue * fuelQuantity
}

// Розраховуємо викиди для кожного типу палива
fun calculateEmissions(
    coalQuantity: Double,
    fuelOilQuantity: Double,
    gasQuantity: Double
): Map<String, EmissionResult> {
    val fuelCalculations = mapOf(
        "вугілля" to { calculateCoalEmission(coalQuantity) },
        "мазута" to { calculateFuelOilEmission(fuelOilQuantity) },
        "газу" to { calculateGasEmission(gasQuantity) }
    )

    return fuelCalculations.mapValues { it.value() }
}

package com.mking11.etiquette.features.countries.domain.models

import com.mking11.etiquette.features.countries.domain.models.CountriesDbo

data class CountriesDto(
    val countryId: String = "",
    val countryName: String = "",
    val photo: String = ""
) {
    fun toDb(): CountriesDbo {
        return CountriesDbo(countryId, countryName, photo)
    }
}
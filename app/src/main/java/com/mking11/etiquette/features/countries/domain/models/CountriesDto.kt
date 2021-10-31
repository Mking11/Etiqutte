package com.mking11.etiquette.features.countries.domain.models

import android.content.Context
import android.graphics.Bitmap
import coil.ImageLoader
import com.mking11.etiquette.common.utils.photo_room_utils.convertToBitmap
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo

data class CountriesDto(
    val countryId: String = "",
    val countryName: String = "",
    val photo: String = ""
) {
    fun toDb(bitmap: Bitmap): CountriesDbo {
        return CountriesDbo(countryId, countryName, bitmap)
    }
}
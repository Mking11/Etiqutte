package com.mking11.etiquette.features.countries.domain.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class CountriesDbo(
    @PrimaryKey
    val countryId: String,
    val countryName: String,
    val photo: Bitmap
)
package com.mking11.etiquette.features.categories.domain.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoryDbo(
    @PrimaryKey
    val categoryId: String,
    val name: String,
    val photo: Bitmap?=null
)
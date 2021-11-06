package com.mking11.etiquette.features.categories.domain.models

import android.graphics.Bitmap

data class CategoryDto(
    val categoryId: String ="",
    val name: String = "",
    val photo: String? =null
) {
    fun toDb(drawable:Bitmap?): CategoryDbo {
        return CategoryDbo(categoryId, name, drawable)
    }
}
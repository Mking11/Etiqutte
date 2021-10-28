package com.mking11.etiquette.features.categories.domain.models

data class CategoryDto(
    val categoryId: String,
    val name: String,
    val photo: String
) {
    fun toDb(): CategoryDbo {
        return CategoryDbo(categoryId, name, photo)
    }
}
package com.mking11.etiquette.features.categories.domain.use_cases

import com.mking11.etiquette.features.categories.CategoriesRepository
import com.mking11.etiquette.features.categories.domain.models.CategoryDto

class InsertCategoriesDb(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(categories: HashMap<String, CategoryDto>) {
        categoriesRepository.insertCategoryDb(categories)
    }
}
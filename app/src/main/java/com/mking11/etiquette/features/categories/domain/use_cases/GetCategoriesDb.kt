package com.mking11.etiquette.features.categories.domain.use_cases

import com.mking11.etiquette.features.categories.CategoriesRepository
import com.mking11.etiquette.features.categories.domain.models.CategoryDbo
import kotlinx.coroutines.flow.Flow

class GetCategoriesDb(private val categoriesRepository: CategoriesRepository) {
    operator fun invoke(): Flow<List<CategoryDbo>>? {
        return categoriesRepository.getCategoryDb()
    }
}
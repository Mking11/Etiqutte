package com.mking11.etiquette.features.categories.domain.use_cases

import com.mking11.etiquette.features.categories.CategoriesRepository

class CloseCategoriesRemote(private val categoriesRepository: CategoriesRepository) {
    operator fun invoke() {
        categoriesRepository.closeRepository()
    }
}
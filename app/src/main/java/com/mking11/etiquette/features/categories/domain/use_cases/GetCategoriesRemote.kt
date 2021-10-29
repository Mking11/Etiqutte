package com.mking11.etiquette.features.categories.domain.use_cases

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.categories.CategoriesRepository
import com.mking11.etiquette.features.categories.domain.models.CategoryDto

class GetCategoriesRemote(private val categoriesRepository: CategoriesRepository) {
    operator fun invoke(): LiveData<HashMap<String, CategoryDto>> {

        return categoriesRepository.getCategoriesRemote()
    }
}
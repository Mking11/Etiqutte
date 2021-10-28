package com.mking11.etiquette.features.categories

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.categories.domain.models.CategoryDbo
import com.mking11.etiquette.features.categories.domain.models.CategoryDto
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategoriesRemote(): LiveData<HashMap<String, CategoryDto>>
    fun getCategoryDb(): Flow<List<CategoryDbo>>?
    fun insertCategoryDb(categories:HashMap<String,CategoryDto>)
    fun closeRepository()
}
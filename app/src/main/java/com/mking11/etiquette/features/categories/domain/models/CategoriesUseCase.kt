package com.mking11.etiquette.features.categories.domain.models

import com.mking11.etiquette.features.categories.domain.use_cases.CloseCategoriesRemote
import com.mking11.etiquette.features.categories.domain.use_cases.GetCategoriesDb
import com.mking11.etiquette.features.categories.domain.use_cases.GetCategoriesRemote
import com.mking11.etiquette.features.categories.domain.use_cases.InsertCategoriesDb

data class CategoriesUseCase(

    val closeCategoriesRemote: CloseCategoriesRemote,
    val getCategoriesRemote: GetCategoriesRemote,
    val insertCategoriesDb: InsertCategoriesDb,
    val getCategoriesDb: GetCategoriesDb
)
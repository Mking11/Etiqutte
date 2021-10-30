package com.mking11.etiquette.features.categories.presentation

import androidx.lifecycle.ViewModel
import com.mking11.etiquette.features.categories.domain.models.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
):ViewModel(){

    val categories = categoriesUseCase.getCategoriesDb.invoke()



















}
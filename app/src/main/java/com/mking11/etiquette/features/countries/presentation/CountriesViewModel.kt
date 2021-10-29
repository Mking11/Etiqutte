package com.mking11.etiquette.features.countries.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mking11.etiquette.features.countries.domain.models.CountriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCases: CountriesUseCases
) : ViewModel() {

    val countries = countriesUseCases.getCountriesDb.invoke()
}
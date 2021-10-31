package com.mking11.etiquette.features.countries.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import coil.ImageLoader
import com.mking11.etiquette.features.countries.domain.models.CountriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCases: CountriesUseCases,
    val imageLoader: ImageLoader
) : ViewModel() {

    val countries = countriesUseCases.getCountriesDb.invoke()?.distinctUntilChanged()
}
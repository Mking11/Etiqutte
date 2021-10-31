package com.mking11.etiquette.features.countries

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import com.mking11.etiquette.features.countries.domain.models.CountriesDto
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    fun getCountriesRemote(): LiveData<HashMap<String, CountriesDto>>
    fun getCountriesDb(): Flow<List<CountriesDbo>>?
    suspend fun insertCountriesDb(countries: HashMap<String, CountriesDto>)
    fun closeRepository()
}
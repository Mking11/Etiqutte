package com.mking11.etiquette.features.countries.domain.use_cases

import com.mking11.etiquette.features.countries.CountriesRepository
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import kotlinx.coroutines.flow.Flow

class GetCountriesDb(private val countriesRepository: CountriesRepository) {
    operator fun invoke(): Flow<List<CountriesDbo>>? {
        return countriesRepository.getCountriesDb()
    }
}
package com.mking11.etiquette.features.countries.domain.use_cases

import com.mking11.etiquette.features.countries.CountriesRepository
import com.mking11.etiquette.features.countries.domain.models.CountriesDto

class InsertCountriesDb(private val countriesRepository: CountriesRepository) {

    operator fun invoke(countries: HashMap<String, CountriesDto>) {
        countriesRepository.insertCountriesDb(countries)
    }
}
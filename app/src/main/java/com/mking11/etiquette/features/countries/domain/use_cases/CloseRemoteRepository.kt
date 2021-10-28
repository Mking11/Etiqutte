package com.mking11.etiquette.features.countries.domain.use_cases

import com.mking11.etiquette.features.countries.CountriesRepository

class CloseRemoteRepository(private val countriesRepository: CountriesRepository) {

    operator fun invoke(){
        countriesRepository.closeRepository()
    }
}
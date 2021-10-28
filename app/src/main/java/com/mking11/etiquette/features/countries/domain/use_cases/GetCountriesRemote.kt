package com.mking11.etiquette.features.countries.domain.use_cases

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.countries.CountriesRepository
import com.mking11.etiquette.features.countries.domain.models.CountriesDto

class GetCountriesRemote(private val countriesRepository: CountriesRepository) {
    operator fun invoke(): LiveData<HashMap<String, CountriesDto>> {
        return countriesRepository.getCountriesRemote()
    }
}
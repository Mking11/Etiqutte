package com.mking11.etiquette.features.countries.domain.models

import com.mking11.etiquette.features.countries.domain.use_cases.CloseRemoteRepository
import com.mking11.etiquette.features.countries.domain.use_cases.GetCountriesDb
import com.mking11.etiquette.features.countries.domain.use_cases.GetCountriesRemote
import com.mking11.etiquette.features.countries.domain.use_cases.InsertCountriesDb

data class CountriesUseCases(
    val closeRemoteRepository: CloseRemoteRepository,
    val getCountriesDb: GetCountriesDb,
    val getCountriesRemote: GetCountriesRemote,
    val insertCountriesDb: InsertCountriesDb
)
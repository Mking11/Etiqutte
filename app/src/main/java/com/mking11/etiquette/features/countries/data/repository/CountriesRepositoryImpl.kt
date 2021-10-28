package com.mking11.etiquette.features.countries.data.repository

import androidx.lifecycle.LiveData
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.dao_utils.DaoRepo
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.countries.CountriesRepository
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import com.mking11.etiquette.features.countries.domain.models.CountriesDto
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesDao
import kotlinx.coroutines.flow.Flow

class CountriesRepositoryImpl(
    private val countriesDao: CountriesDao,
    private val countriesRemote: FirebaseValueDataSource<CountriesDto>,
    private val firebaseCrash: FirebaseCrash
) : CountriesRepository, DaoRepo<CountriesDbo, String, CountriesDao>(
    countriesDao,
    "CountriesRepositoryImpl",
    firebaseCrash
) {
    override fun getCountriesRemote(): LiveData<HashMap<String, CountriesDto>> {
        return countriesRemote.repoHashedLive
    }

    override fun getCountriesDb(): Flow<List<CountriesDbo>>? {
        return getItems()

    }

    override fun insertCountriesDb(countries: HashMap<String, CountriesDto>) {
        countries.values.forEach {
            insertOrUpdate(it.toDb())
        }
    }

    override fun closeRepository() {
        countriesRemote.closeRepository()
    }
}
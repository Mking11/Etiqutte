package com.mking11.etiquette.features.countries.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import coil.ImageLoader
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.dao_utils.DaoRepo
import com.mking11.etiquette.common.utils.photo_room_utils.convertToBitmap
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.countries.CountriesRepository
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import com.mking11.etiquette.features.countries.domain.models.CountriesDto
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesDao
import kotlinx.coroutines.flow.Flow

class CountriesRepositoryImpl(
    private val countriesDao: CountriesDao,
    private val context: Context,
    private val imageLoader: ImageLoader,
    private val countriesRemote: FirebaseValueDataSource<CountriesDto>,
    private val firebaseCrash: FirebaseCrash
) : CountriesRepository, DaoRepo<CountriesDbo, String, CountriesDao>(
    countriesDao,
    "CountriesRepositoryImpl",
    firebaseCrash
) {
    override fun getCountriesRemote(): LiveData<HashMap<String, CountriesDto>> {
        countriesRemote.observeValue()
        return countriesRemote.repoHashedLive
    }

    override fun getCountriesDb(): Flow<List<CountriesDbo>>? {
        return getItems()

    }

    override suspend fun insertCountriesDb(countries: HashMap<String, CountriesDto>) {
        countries.values.forEach {
            val convertToBitmap = convertToBitmap(context, imageLoader, it.photo)
            if (convertToBitmap != null) {
                insertOrUpdate(it.toDb(convertToBitmap))
            }
        }
    }

    override fun closeRepository() {
        countriesRemote.closeRepository()
    }
}
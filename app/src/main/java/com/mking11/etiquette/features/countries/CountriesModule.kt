package com.mking11.etiquette.features.countries

import com.mking11.etiquette.common.EtiquetteDatabase
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.categories.domain.models.CategoriesUseCase
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesDao
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesFirebaseSource
import com.mking11.etiquette.features.countries.data.repository.CountriesRepositoryImpl
import com.mking11.etiquette.features.countries.domain.models.CountriesDto
import com.mking11.etiquette.features.countries.domain.models.CountriesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesModule {
    @Provides
    @Singleton
    fun provideCountriesDao(database: EtiquetteDatabase): CountriesDao {
        return database.countriesDao
    }

    @Provides
    @Singleton
    fun provideCountriesFirebaseSource(
        firebaseRealDb: FirebaseRealDb,
        firebaseCrash: FirebaseCrash
    ): FirebaseValueDataSource<CountriesDto> {
        return CountriesFirebaseSource(firebaseRealDb, firebaseCrash)
    }

    @Provides
    @Singleton
    fun providesCountriesRepository(
        countriesDao: CountriesDao,
        countriesRemote: FirebaseValueDataSource<CountriesDto>,
        firebaseCrash: FirebaseCrash
    ): CountriesRepository {
        return CountriesRepositoryImpl(countriesDao, countriesRemote, firebaseCrash)
    }

    @Provides
    @Singleton
    fun providesCountriesUseCases(
        countriesRepository: CountriesRepository
    ): CountriesUseCases {
        return CategoriesUseCase(

        )
    }
}
package com.mking11.etiquette.features.countries

import com.mking11.etiquette.common.EtiquetteDatabase
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesDao
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesFirebaseSource
import com.mking11.etiquette.features.countries.data.repository.CountriesRepositoryImpl
import com.mking11.etiquette.features.countries.domain.models.CountriesDto
import com.mking11.etiquette.features.countries.domain.models.CountriesUseCases
import com.mking11.etiquette.features.countries.domain.use_cases.CloseRemoteRepository
import com.mking11.etiquette.features.countries.domain.use_cases.GetCountriesDb
import com.mking11.etiquette.features.countries.domain.use_cases.GetCountriesRemote
import com.mking11.etiquette.features.countries.domain.use_cases.InsertCountriesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CountriesModule {
    @Provides
    @ViewModelScoped
    fun provideCountriesDao(database: EtiquetteDatabase): CountriesDao {
        return database.countriesDao
    }

    @Provides
    @ViewModelScoped
    fun provideCountriesFirebaseSource(
        firebaseRealDb: FirebaseRealDb,
        firebaseCrash: FirebaseCrash
    ): FirebaseValueDataSource<CountriesDto> {
        return CountriesFirebaseSource(firebaseRealDb, firebaseCrash)
    }

    @Provides
    @ViewModelScoped
    fun providesCountriesRepository(
        countriesDao: CountriesDao,
        countriesRemote: FirebaseValueDataSource<CountriesDto>,
        firebaseCrash: FirebaseCrash
    ): CountriesRepository {
        return CountriesRepositoryImpl(countriesDao, countriesRemote, firebaseCrash)
    }

    @Provides
    @ViewModelScoped
    fun providesCountriesUseCases(
        countriesRepository: CountriesRepository
    ): CountriesUseCases {
        return CountriesUseCases(
            closeRemoteRepository = CloseRemoteRepository(countriesRepository),
            getCountriesDb = GetCountriesDb(countriesRepository),
            getCountriesRemote = GetCountriesRemote(countriesRepository),
            insertCountriesDb = InsertCountriesDb(countriesRepository )
        )
    }
}
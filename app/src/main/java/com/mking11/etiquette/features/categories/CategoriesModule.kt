package com.mking11.etiquette.features.categories

import com.mking11.etiquette.common.EtiquetteDatabase
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.categories.data.data_soruce.CategoriesDao
import com.mking11.etiquette.features.categories.data.data_soruce.CategoriesFirebaseSource
import com.mking11.etiquette.features.categories.data.repository.CategoriesRepositoryImpl
import com.mking11.etiquette.features.categories.domain.models.CategoriesUseCase
import com.mking11.etiquette.features.categories.domain.models.CategoryDto
import com.mking11.etiquette.features.categories.domain.use_cases.CloseCategoriesRemote
import com.mking11.etiquette.features.categories.domain.use_cases.GetCategoriesDb
import com.mking11.etiquette.features.categories.domain.use_cases.GetCategoriesRemote
import com.mking11.etiquette.features.categories.domain.use_cases.InsertCategoriesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {

    @Provides
    @Singleton
    fun provideCategoriesDao(database: EtiquetteDatabase): CategoriesDao {
        return database.categoriesDao
    }

    @Provides
    @Singleton
    fun provideCategoryFirebaseSource(
        firebaseRealDb: FirebaseRealDb,
        firebaseCrash: FirebaseCrash
    ): FirebaseValueDataSource<CategoryDto> {
        return CategoriesFirebaseSource(firebaseRealDb, firebaseCrash)
    }

    @Provides
    @Singleton
    fun providesCategoriesRepository(
        categoriesDao: CategoriesDao,
        categoryRemoteSource: FirebaseValueDataSource<CategoryDto>,
        firebaseCrash: FirebaseCrash
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(categoryRemoteSource, categoriesDao, firebaseCrash)
    }

    @Provides
    @Singleton
    fun providesCategoriesUseCases(
        categoriesRepository: CategoriesRepository
    ): CategoriesUseCase {
        return CategoriesUseCase(
            closeCategoriesRemote = CloseCategoriesRemote(categoriesRepository),
            getCategoriesRemote = GetCategoriesRemote(categoriesRepository),
            insertCategoriesDb = InsertCategoriesDb(categoriesRepository),
            getCategoriesDb = GetCategoriesDb(categoriesRepository)
        )
    }


}
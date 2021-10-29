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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CategoriesModule {

    @Provides
    @ViewModelScoped
    fun provideCategoriesDao(database: EtiquetteDatabase): CategoriesDao {
        return database.categoriesDao
    }

    @Provides
    @ViewModelScoped
    fun provideCategoryFirebaseSource(
        firebaseRealDb: FirebaseRealDb,
        firebaseCrash: FirebaseCrash
    ): FirebaseValueDataSource<CategoryDto> {
        return CategoriesFirebaseSource(firebaseRealDb, firebaseCrash)
    }

    @Provides
    @ViewModelScoped
    fun providesCategoriesRepository(
        categoriesDao: CategoriesDao,
        categoryRemoteSource: FirebaseValueDataSource<CategoryDto>,
        firebaseCrash: FirebaseCrash
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(categoryRemoteSource, categoriesDao, firebaseCrash)
    }

    @Provides
    @ViewModelScoped
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
package com.mking11.etiquette.features.categories.data.repository

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import coil.ImageLoader
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.dao_utils.DaoRepo
import com.mking11.etiquette.common.utils.photo_room_utils.convertToBitmap
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.categories.CategoriesRepository
import com.mking11.etiquette.features.categories.data.data_soruce.CategoriesDao
import com.mking11.etiquette.features.categories.domain.models.CategoryDbo
import com.mking11.etiquette.features.categories.domain.models.CategoryDto
import kotlinx.coroutines.flow.Flow

class CategoriesRepositoryImpl(
    private val categoriesRemoteSource: FirebaseValueDataSource<CategoryDto>,
    private val categoriesDao: CategoriesDao,
    private val context: Context,
    private val imageLoader: ImageLoader,
    firebaseCrash: FirebaseCrash
) : CategoriesRepository, DaoRepo<CategoryDbo, String, CategoriesDao>(
    categoriesDao,
    "CategoriesRepositoryImpl",
    firebaseCrash
) {
    override fun getCategoriesRemote(): LiveData<HashMap<String, CategoryDto>> {
        categoriesRemoteSource.observeValue()
        return categoriesRemoteSource.repoHashedLive
    }

    override fun getCategoryDb(): Flow<List<CategoryDbo>>? {
        return getItems()
    }

    override suspend fun insertCategoryDb(categories: HashMap<String, CategoryDto>) {
        categories.values.forEach {
            val drawable: Bitmap? = it.photo?.let { it1 ->
                convertToBitmap(context,imageLoader,
                    it1
                )
            }

            println("insert called ${drawable}")
            insertOrUpdate(it.toDb(drawable))
        }
    }

    override fun closeRepository() {
        categoriesRemoteSource.closeRepository()
    }


}
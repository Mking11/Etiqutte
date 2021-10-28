package com.mking11.etiquette.features.categories.data.data_soruce;

import androidx.room.Dao
import androidx.room.Query
import com.mking11.etiquette.common.utils.repo_utils.DaoCommon
import com.mking11.etiquette.features.categories.domain.models.CategoryDbo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CategoriesDao : DaoCommon<CategoryDbo, String> {

    @Query("Select * From  Categories ")
    abstract override fun getItems(): Flow<List<CategoryDbo>>?

    @Query("Delete  from Categories where categoryId not in (:ids)")
    abstract override suspend fun clearSelected(ids: List<String>)

    @Query("Delete from Categories ")
    abstract override suspend fun clear()


}
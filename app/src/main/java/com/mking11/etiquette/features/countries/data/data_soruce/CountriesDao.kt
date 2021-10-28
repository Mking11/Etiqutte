package com.mking11.etiquette.features.countries.data.data_soruce;

import androidx.room.*
import com.mking11.etiquette.common.utils.repo_utils.DaoCommon
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CountriesDao : DaoCommon<CountriesDbo, String> {

    @Query("Select * From Countries where countryId=:id")
    abstract override suspend fun getItem(id: String): CountriesDbo?

    @Query("Select * From  Countries ")
    abstract override fun getItems(): Flow<List<@JvmSuppressWildcards CountriesDbo>>?

    @Query("Select * From Countries where countryId in (:ids)")
    abstract override fun getSelectedItems(ids: List<String>): Flow<List<@JvmSuppressWildcards CountriesDbo>>?

    @Query("Delete  from Countries where countryId not in (:ids)")
    abstract override suspend fun clearSelected(ids: List<String>)

    @Query("Delete from Countries ")
    abstract override suspend fun clear()


}
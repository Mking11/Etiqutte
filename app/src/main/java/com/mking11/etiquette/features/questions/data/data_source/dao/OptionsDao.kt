package com.mking11.etiquette.features.questions.data.data_source.dao;

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.mking11.etiquette.common.utils.repo_utils.DaoCommon
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

@Dao
abstract class OptionsDao : DaoCommon<OptionsDbo, String> {

    @Query("Select * From Options where id in (:ids)")
    abstract override fun getSelectedItems(ids: List<String>): Flow<List<OptionsDbo>>?

    @Query("Select * From Options where questionId=:id")
    abstract fun getOptionItems(id:String): Flow<List<OptionsDbo>>?

    @Query("Delete  from Options where id not in (:ids)")
    abstract override suspend fun clearSelected(ids: List<String>)

    @Query("Delete from Options ")
    abstract override suspend fun clear()


}
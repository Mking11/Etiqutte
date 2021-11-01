package com.mking11.etiquette.features.questions.data.data_source.dao;

import androidx.room.Dao
import androidx.room.Query
import com.mking11.etiquette.common.utils.repo_utils.DaoCommon
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class QuestionsDao : DaoCommon<QuestionsDbo, String> {

    @Query("Select * From Questions where id=:id")
    abstract override suspend fun getItem(id: String): QuestionsDbo?

    @Query("Select * From  Questions where countryId=:countryId and categoryId=:categoryId ")
    abstract fun getItems(countryId: String, categoryId: String): Flow<List<QuestionsDbo>>?

    @Query("Delete  from Questions where id not in (:ids)")
    abstract override suspend fun clearSelected(ids: List<String>)

    @Query("Delete from Questions ")
    abstract override suspend fun clear()

    @Query("Select * from Questions")
    abstract override fun getItems(): Flow<List<QuestionsDbo>>?

    @Query("Select * From  Questions where id in(:ids) ")
    abstract override fun getSelectedItems(ids: List<String>): Flow<List<QuestionsDbo>>?
}
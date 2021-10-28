package com.mking11.etiquette.features.questions

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo
import com.mking11.etiquette.features.questions.domain.models.dto.OptionsDto
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    fun getQuestionsRemote(): LiveData<HashMap<String, QuestionsDto>>
    suspend fun insertQuestionsDb(list: List<QuestionsDto>)
    suspend fun insertOptionsDb(questionId: String, list: HashMap<String, OptionsDto>)
    suspend fun getQuestion(id: String): QuestionsDbo?
    fun getQuestions(categoryId: String, countryId: String): Flow<List<QuestionsDbo>>?
    fun closeRepository()
}


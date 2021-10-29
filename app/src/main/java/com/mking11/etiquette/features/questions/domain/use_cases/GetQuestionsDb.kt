package com.mking11.etiquette.features.questions.domain.use_cases

import com.mking11.etiquette.features.questions.QuestionsRepository
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo
import kotlinx.coroutines.flow.Flow

class GetQuestionsDb(private val questionsRepository: QuestionsRepository) {

    operator fun invoke(countriesId:String,categories:String): Flow<List<QuestionsDbo>>? {
        return questionsRepository.getQuestions(categories,countriesId)
    }
}
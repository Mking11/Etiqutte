package com.mking11.etiquette.features.questions.domain.use_cases

import com.mking11.etiquette.features.questions.QuestionsRepository
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto

class InsertQuestions(private val questionsRepository: QuestionsRepository) {

    suspend operator fun invoke(list: List<QuestionsDto>) {


        questionsRepository.insertQuestionsDb(list)

    }
}
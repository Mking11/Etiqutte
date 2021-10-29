package com.mking11.etiquette.features.questions.domain.use_cases

import com.mking11.etiquette.features.questions.QuestionsRepository

class CloseRemote(private val questionsRepository: QuestionsRepository) {
    operator fun invoke() {
        questionsRepository.closeRepository()
    }
}
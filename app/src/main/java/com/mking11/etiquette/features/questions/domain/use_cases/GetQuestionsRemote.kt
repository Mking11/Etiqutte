package com.mking11.etiquette.features.questions.domain.use_cases

import androidx.lifecycle.LiveData
import com.mking11.etiquette.features.questions.QuestionsRepository
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto

class GetQuestionsRemote(private val questionsRepository: QuestionsRepository) {
    operator fun invoke(): LiveData<HashMap<String, QuestionsDto>> {
        return questionsRepository.getQuestionsRemote()
    }
}
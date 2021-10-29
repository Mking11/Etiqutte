package com.mking11.etiquette.features.questions.domain.use_cases

import com.mking11.etiquette.features.questions.OptionsRepository
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import kotlinx.coroutines.flow.Flow

class GetOptionsDb(private val optionsRepository: OptionsRepository) {
    operator fun invoke(questionId:String): Flow<List<OptionsDbo>>? {
        return optionsRepository.getOptions(questionId)
    }
}
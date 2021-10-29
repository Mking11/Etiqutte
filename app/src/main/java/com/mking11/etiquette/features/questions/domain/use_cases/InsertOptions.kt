package com.mking11.etiquette.features.questions.domain.use_cases

import com.mking11.etiquette.features.questions.OptionsRepository
import com.mking11.etiquette.features.questions.domain.models.dto.OptionsDto

class InsertOptions(private val optionsRepository: OptionsRepository) {

    suspend operator fun invoke(questionId:String, options:HashMap<String,OptionsDto>){
        optionsRepository.insertOptionsDb(questionId,options)
    }
}
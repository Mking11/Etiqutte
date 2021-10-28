package com.mking11.etiquette.features.questions

import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import com.mking11.etiquette.features.questions.domain.models.dto.OptionsDto
import kotlinx.coroutines.flow.Flow

interface OptionsRepository{
    suspend fun insertOptionsDb(questionId:String,list: HashMap<String, OptionsDto>)
    fun getOptions(id:String): Flow<List<OptionsDbo>>?
}
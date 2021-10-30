package com.mking11.etiquette.features.questions.presentations

import androidx.lifecycle.ViewModel
import com.mking11.etiquette.features.questions.domain.models.QuestionsUseCases
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val questionsUseCases: QuestionsUseCases):ViewModel() {



    fun getQuestions(categories:String,countryId:String): Flow<List<QuestionsDbo>>? {
        return questionsUseCases.getQuestionsDb(countryId,categories)
    }


}
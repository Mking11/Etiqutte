package com.mking11.etiquette.features.questions.presentations

import androidx.lifecycle.ViewModel
import coil.ImageLoader
import com.mking11.etiquette.features.questions.domain.models.QuestionsUseCases
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val questionsUseCases: QuestionsUseCases,
    val imageLoader: ImageLoader
) : ViewModel() {


    fun getOptions(questionId: String): Flow<List<OptionsDbo>>? {
        return questionsUseCases.getOptionsDb(questionId)?.map {
            it.sortedBy {
                it.title
            }
        }?.distinctUntilChanged()
    }
}
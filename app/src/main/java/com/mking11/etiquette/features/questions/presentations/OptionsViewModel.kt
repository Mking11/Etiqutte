package com.mking11.etiquette.features.questions.presentations

import androidx.lifecycle.ViewModel
import coil.ImageLoader
import com.mking11.etiquette.features.categories.domain.models.CategoriesUseCase
import com.mking11.etiquette.features.questions.domain.models.QuestionsUseCases
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class OptionsViewModel  @Inject constructor(
    private val questionsUseCases: QuestionsUseCases,
    val imageLoader: ImageLoader
):ViewModel() {


    fun getOptions(quesitonId:String): Flow<List<OptionsDbo>>? {
        return questionsUseCases.getOptionsDb(quesitonId)
    }
}
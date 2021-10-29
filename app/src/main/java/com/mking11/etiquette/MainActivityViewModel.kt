package com.mking11.etiquette

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.features.categories.domain.models.CategoriesUseCase
import com.mking11.etiquette.features.countries.domain.models.CountriesUseCases
import com.mking11.etiquette.features.questions.domain.models.QuestionsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val questionsUseCases: QuestionsUseCases,
    private val categoriesUseCase: CategoriesUseCase,
    private val countriesUseCases: CountriesUseCases,
    private val firebaseCrash: FirebaseCrash
) : ViewModel() {


    private val handler = CoroutineExceptionHandler { _, e ->
        firebaseCrash.setErrorToFireBase(e, " MainActivityViewModel.kt  20: ")
    }

    fun getQuestions() = viewModelScope.launch(handler) {
        questionsUseCases.getQuestionsRemote().asFlow().catch { e ->
            firebaseCrash.setErrorToFireBase(e, "getQuestions MainActivityViewModel.kt  33: ")
        }.collect {

            println("observed ${it}")
            it.values.forEach {
                questionsUseCases.insertOptions(it.id, it.options)
            }
            questionsUseCases.insertQuestions(it.values.toList())
        }
    }

    fun getCategories() = viewModelScope.launch(handler) {
        categoriesUseCase.getCategoriesRemote.invoke().asFlow().catch { e ->
            firebaseCrash.setErrorToFireBase(e, "getCategories MainActivityViewModel.kt  48: ")
        }.collect {
            categoriesUseCase.insertCategoriesDb.invoke(it)
        }
    }

    fun getCountries() = viewModelScope.launch(handler) {
        countriesUseCases.getCountriesRemote.invoke().asFlow().catch { e ->
            firebaseCrash.setErrorToFireBase(e, "getCountries MainActivityViewModel.kt  57: ")
        }.collect {
            countriesUseCases.insertCountriesDb(it)
        }
    }


    override fun onCleared() {
        countriesUseCases.closeRemoteRepository.invoke()
        categoriesUseCase.closeCategoriesRemote.invoke()
        questionsUseCases.closeRemote.invoke()
        super.onCleared()
    }
}
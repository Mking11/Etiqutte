package com.mking11.etiquette.features.questions.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import coil.ImageLoader
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.dao_utils.DaoRepo
import com.mking11.etiquette.common.utils.photo_room_utils.convertToBitmap
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.questions.OptionsRepository
import com.mking11.etiquette.features.questions.QuestionsRepository
import com.mking11.etiquette.features.questions.data.data_source.dao.QuestionsDao
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo
import com.mking11.etiquette.features.questions.domain.models.dto.OptionsDto
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto
import kotlinx.coroutines.flow.Flow

class QuestionsRepositoryImpl(
    firebaseCrash: FirebaseCrash,
    private val questionsDao: QuestionsDao,
    private val optionRepo: OptionsRepository,
    private val context: Context,
    private val imageLoader: ImageLoader,
    private val questionsRemote: FirebaseValueDataSource<QuestionsDto>
) : QuestionsRepository,
    DaoRepo<QuestionsDbo, String, QuestionsDao>(
        questionsDao,
        "QuestionsRepositoryImpl",
        firebaseCrash
    ) {

    override fun getQuestionsRemote(): LiveData<HashMap<String, QuestionsDto>> {
        questionsRemote.observeValue()
        return questionsRemote.repoHashedLive
    }

    override suspend fun insertQuestionsDb(list: List<QuestionsDto>) {
        list.forEach { question ->
            val bitmap = question.photo?.let { convertToBitmap(context, imageLoader, it) }
            insertOrUpdate(question.toDb(bitmap))
        }

    }

    override suspend fun insertOptionsDb(questionId: String, list: HashMap<String, OptionsDto>) {
        optionRepo.insertOptionsDb(questionId, list)
    }

    override suspend fun getQuestion(id: String): QuestionsDbo? {
        return getItem(id)
    }

    override fun getQuestions(categoryId: String, countryId: String): Flow<List<QuestionsDbo>>? {
        return questionsDao.getItems(countryId, categoryId)
    }

    override fun closeRepository() {
        return questionsRemote.closeRepository()
    }
}
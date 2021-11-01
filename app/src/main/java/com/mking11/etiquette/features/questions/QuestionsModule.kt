package com.mking11.etiquette.features.questions

import android.content.Context
import coil.ImageLoader
import com.mking11.etiquette.common.EtiquetteDatabase
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.questions.data.data_source.dao.OptionsDao
import com.mking11.etiquette.features.questions.data.data_source.dao.QuestionsDao
import com.mking11.etiquette.features.questions.data.data_source.firebase_sources.QuestionsFirebaseSource
import com.mking11.etiquette.features.questions.data.repository.OptionsRepositoryImpl
import com.mking11.etiquette.features.questions.data.repository.QuestionsRepositoryImpl
import com.mking11.etiquette.features.questions.domain.models.QuestionsUseCases
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto
import com.mking11.etiquette.features.questions.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object QuestionsModule {

    @Provides
    @ViewModelScoped
    fun provideQuestionsDao(database: EtiquetteDatabase): QuestionsDao {
        return database.questionsDao
    }

    @Provides
    @ViewModelScoped
    fun provideOptionsDao(database: EtiquetteDatabase): OptionsDao {
        return database.optionsDao
    }


    @Provides
    @ViewModelScoped
    fun provideQuestionsFirebaseSource(
        firebaseRealDb: FirebaseRealDb,
        firebaseCrash: FirebaseCrash
    ): FirebaseValueDataSource<QuestionsDto> {
        return QuestionsFirebaseSource(firebaseRealDb, firebaseCrash)
    }


    @Provides
    @ViewModelScoped
    fun providesOptionsRepository(
        optionsDao: OptionsDao,
        firebaseCrash: FirebaseCrash
    ): OptionsRepository {
        return OptionsRepositoryImpl(optionsDao, firebaseCrash)
    }

    @Provides
    @ViewModelScoped
    fun providesQuestionsRepository(
        questionsDao: QuestionsDao,
        optionsRepository: OptionsRepository,
        @ApplicationContext context: Context,
        imageLoader: ImageLoader,
        questionsRemoteSource: FirebaseValueDataSource<QuestionsDto>,
        firebaseCrash: FirebaseCrash
    ): QuestionsRepository {
        return QuestionsRepositoryImpl(
            firebaseCrash,
            questionsDao,
            optionsRepository,
            context, imageLoader,
            questionsRemoteSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideQuestionsUseCases(
        questionsRepository: QuestionsRepository,
        optionsRepository: OptionsRepository
    ): QuestionsUseCases {
        return QuestionsUseCases(
            closeRemote = CloseRemote(questionsRepository),
            getQuestionsDb = GetQuestionsDb(questionsRepository = questionsRepository),
            getQuestionsRemote = GetQuestionsRemote(questionsRepository),
            insertOptions = InsertOptions(optionsRepository),
            getOptionsDb = GetOptionsDb(optionsRepository),
            insertQuestions = InsertQuestions(questionsRepository)
        )
    }


}
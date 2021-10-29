package com.mking11.etiquette.features.questions.domain.models

import com.mking11.etiquette.features.questions.domain.use_cases.*

data class QuestionsUseCases(
    val closeRemote: CloseRemote,
    val getQuestionsDb: GetQuestionsDb,
    val getQuestionsRemote: GetQuestionsRemote,
    val insertQuestions: InsertQuestions,
    val insertOptions: InsertOptions,
    val getOptionsDb: GetOptionsDb
)
package com.mking11.etiquette.features.questions.domain.models.dto

import android.graphics.Bitmap
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo

data class QuestionsDto(
    val id: String ="",
    val categoryId: String="",
    val title: String="",
    val validAnswer: String ="",
    val photo: String? = null,
    val countryId: String = "",
    val options: HashMap<String, OptionsDto> = hashMapOf()
) {
    fun toDb(bitmap: Bitmap?): QuestionsDbo {
        return QuestionsDbo(
            id,
            categoryId,
            title,
            validAnswer,
            bitmap,
            countryId,
            options.toString()
        )
    }
}


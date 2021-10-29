package com.mking11.etiquette.features.questions.domain.models.dto

import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo

data class OptionsDto(
    val id: String= "",
    val title: String ="",
) {
    fun toDb(questionId: String): OptionsDbo {
        return OptionsDbo(id, questionId, title)
    }
}
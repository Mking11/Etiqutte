package com.mking11.etiquette.features.questions.domain.models.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Questions")
data class QuestionsDbo(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val title: String,
    val validAnswer: String,
    val photo: String? = null,
    val countryId: String,
    val options: String
)
package com.mking11.etiquette.features.questions.domain.models.dbo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Questions")
data class QuestionsDbo(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val title: String,
    val validAnswer: String,
    val photo: Bitmap? = null,
    val countryId: String,
    val options: String
)
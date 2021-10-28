package com.mking11.etiquette.features.questions.domain.models.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Options")
data class OptionsDbo(
    @PrimaryKey
    val id: String,
    val questionId:String,
    val title: String,
)
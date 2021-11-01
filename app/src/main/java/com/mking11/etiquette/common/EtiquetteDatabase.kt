package com.mking11.etiquette.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mking11.etiquette.common.utils.photo_room_utils.Converters
import com.mking11.etiquette.features.categories.data.data_soruce.CategoriesDao
import com.mking11.etiquette.features.categories.domain.models.CategoryDbo
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import com.mking11.etiquette.features.countries.data.data_soruce.CountriesDao
import com.mking11.etiquette.features.questions.data.data_source.dao.OptionsDao
import com.mking11.etiquette.features.questions.data.data_source.dao.QuestionsDao
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import com.mking11.etiquette.features.questions.domain.models.dbo.QuestionsDbo


@Database(
    entities = [CategoryDbo::class, CountriesDbo::class, QuestionsDbo::class, OptionsDbo::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EtiquetteDatabase : RoomDatabase() {


    abstract val categoriesDao: CategoriesDao
    abstract val countriesDao: CountriesDao
    abstract val questionsDao: QuestionsDao
    abstract val optionsDao: OptionsDao

    companion object {
        val DATABASE_NAME: String = "EtiquetteDatabase"
    }
}
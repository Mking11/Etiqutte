package com.mking11.etiquette.features.questions.data.repository

import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.dao_utils.DaoRepo
import com.mking11.etiquette.features.questions.OptionsRepository
import com.mking11.etiquette.features.questions.data.data_source.dao.OptionsDao
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo
import com.mking11.etiquette.features.questions.domain.models.dto.OptionsDto
import kotlinx.coroutines.flow.Flow

class OptionsRepositoryImpl(
    private val optionsDaoDao: OptionsDao,
    private val firebaseCrash: FirebaseCrash
) : OptionsRepository,
    DaoRepo<OptionsDbo, String, OptionsDao>(optionsDaoDao, "OptionsRepositoryImpl", firebaseCrash) {
    override suspend fun insertOptionsDb(questionId: String, list: HashMap<String, OptionsDto>) {
        list.values.forEach {
            insertOrUpdate(it.toDb(questionId))
        }
    }

    override fun getOptions(id: String): Flow<List<OptionsDbo>>? {
        return optionsDaoDao.getOptionItems(id)
    }


}
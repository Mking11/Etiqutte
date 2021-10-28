package com.mking11.etiquette.common.utils.dao_utils

import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.utils.repo_utils.DaoCommon
import com.mking11.etiquette.common.utils.repo_utils.ScopeShared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class DaoRepo<OutPutType : Any, PrimaryKeyType, daoType : DaoCommon<OutPutType, PrimaryKeyType>>(
    private val dao: daoType,
    fileName: String,
    firebaseCrash: FirebaseCrash
) : ScopeShared(fileName, firebaseCrash) {

    fun insertOrUpdate(item: OutPutType?) = scope.launch(handler) {
        if (item != null) {
            dao.insertOrUpdate(item)
        }
    }

    fun deleteItem(item: OutPutType?) = scope.launch(handler) {
        if (item != null) {
            dao.deleteItem(item)
        }
    }

    fun clearTable() = scope.launch(handler) {
        dao.clear()
    }

    fun clearItems(ids: List<PrimaryKeyType>) = scope.launch(handler) {
        dao.clearSelected(ids)
    }

    fun getItems(): Flow<List<@JvmSuppressWildcards OutPutType>>? = dao.getItems()

    suspend fun getItem(item:PrimaryKeyType) = dao.getItem(item)


    fun getItems(items: List<PrimaryKeyType>): Flow<List<OutPutType>>? =
        dao.getSelectedItems(items)


}
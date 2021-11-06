package com.mking11.etiquette.common.utils


interface DboConvertor<T> {
    fun toDbo(key: String? = null): T? {
        return null
    }
}
package com.mking11.etiquette.common.utils.repo_utils


interface RepoCallback<T> {
    fun onAddedOrChanged(item:T)
    fun onDeleted(item: T)
}
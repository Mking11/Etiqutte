package com.mking11.etiquette.features.categories.data.data_soruce

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.categories.domain.models.CategoryDto


private val TAG = "CategoriesFirebase"

class CategoriesFirebaseSource(
    private val firebaseRealDb: FirebaseRealDb,
    private val firebaseCrash: FirebaseCrash
) :
    FirebaseValueDataSource<CategoryDto>() {
    override val docRef: DatabaseReference
        get() = firebaseRealDb.categories()

    override fun onDataChanged(snapshot: DataSnapshot) {
        try {
            if (snapshot.exists()) {
                val data: HashMap<String, CategoryDto>? =
                    snapshot.getValue<HashMap<String, CategoryDto>>()
                repoHashedLive.value = data ?: hashMapOf()
            }
        } catch (e: Exception) {
            firebaseCrash.setErrorToFireBase(e, "onDataChanged QuestionFirebaseSource.kt  28: ")
        }
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e(TAG, "onCancelled: $error")
    }
}
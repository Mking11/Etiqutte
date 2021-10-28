package com.mking11.etiquette.features.questions.data.data_source.firebase_sources

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.questions.domain.models.dto.QuestionsDto

class QuestionsFirebaseSource(
    private val firebaseRealDb: FirebaseRealDb,
    private val firebaseCrash: FirebaseCrash
) :
    FirebaseValueDataSource<QuestionsDto>() {
    override val docRef: DatabaseReference
        get() = firebaseRealDb.questions()

    override fun onDataChanged(snapshot: DataSnapshot) {
        try {
            if (snapshot.exists()) {
                val data: HashMap<String, QuestionsDto>? =
                    snapshot.getValue<HashMap<String, QuestionsDto>>()
                repoHashedLive.value = data ?: hashMapOf()
            }
        } catch (e: Exception) {
            firebaseCrash.setErrorToFireBase(e, "onDataChanged QuestionFirebaseSource.kt  28: ")
        }
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("QuestionsFirebaseSource", "onCancelled: $error")
    }
}
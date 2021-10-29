package com.mking11.etiquette.common.firebaseutils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mking11.etiquette.common.user_session.data.repository.FirebaseAuthRepository
import javax.inject.Inject

class FirebaseCrash @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository) {
    var firebaseCrashlytics: FirebaseCrashlytics? = null

    fun getInstance(): FirebaseCrashlytics {
        return firebaseCrashlytics ?: FirebaseCrashlytics.getInstance()
    }


    fun setErrorToFireBase(
        e: Throwable? = null,
        source: String
    ) {
        val instance = getInstance()

        if (e != null) {
            instance.setCustomKey("message", e.message.toString())
            instance.setCustomKey("stackTrace", e.stackTraceToString())
            instance.setCustomKey("source", source)
            instance.recordException(e)
        }
    }


    fun setErrorToFireBase(
        e: Exception? = null,
        source: String
    ) {
        val instance = getInstance()


        if (e != null) {
            instance.setCustomKey("message", e.message.toString())
            instance.setCustomKey("stackTrace", e.stackTraceToString())
            instance.setCustomKey("cause", e.cause?.message.toString())
            instance.setCustomKey("source", source)
            instance.recordException(e)
        }
    }
}
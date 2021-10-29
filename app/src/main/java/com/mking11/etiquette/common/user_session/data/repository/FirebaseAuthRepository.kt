package com.mking11.etiquette.common.user_session.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.internal.common.CommonUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class FirebaseAuthRepository(
    private val auth: FirebaseAuth
) {

    fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    fun signInAnonymous(): Flow<String?> = callbackFlow {
        auth.signInAnonymously().addOnSuccessListener {
            trySend(it.user?.uid).isSuccess
        }.addOnFailureListener {
            trySend(it.message).isFailure
        }
    }

    fun checkIfRotted(context: Context): Boolean {
        return CommonUtils.isRooted(context)
    }

    fun checkIfRunningOnEmulator(context: Context): Boolean {
        return CommonUtils.isEmulator(context)
    }

    fun checkIfTheAppIsDebugMode(context: Context): Boolean {
        return CommonUtils.isAppDebuggable(context) || CommonUtils.isDebuggerAttached()
    }

    fun isSingedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        return auth.signOut()
    }


}
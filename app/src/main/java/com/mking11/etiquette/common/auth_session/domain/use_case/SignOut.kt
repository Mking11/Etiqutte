package com.mking11.etiquette.common.auth_session.domain.use_case

import com.mking11.etiquette.common.auth_session.data.repository.FirebaseAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SignOut(private val firebaseAuthRepository: FirebaseAuthRepository) {

    operator fun invoke() {
        firebaseAuthRepository.signOut()
    }
}
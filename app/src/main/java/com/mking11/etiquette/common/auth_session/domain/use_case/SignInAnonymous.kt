package com.mking11.etiquette.common.auth_session.domain.use_case

import com.mking11.etiquette.common.auth_session.data.repository.FirebaseAuthRepository
import com.mking11.etiquette.common.repositories.PreferenceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class SignInAnonymous(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke() {
        firebaseAuthRepository.signInAnonymous().collect {
            preferenceRepository.userId = it
        }
    }
}
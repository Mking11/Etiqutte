package com.mking11.etiquette.common.auth_session

import com.google.firebase.auth.FirebaseAuth
import com.mking11.etiquette.common.auth_session.data.repository.FirebaseAuthRepository
import com.mking11.etiquette.common.auth_session.domain.use_case.SignInAnonymous
import com.mking11.etiquette.common.auth_session.domain.use_case.SignOut
import com.mking11.etiquette.common.auth_session.domain.AuthUseCases
import com.mking11.etiquette.common.repositories.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(auth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepository(auth)
    }

    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    fun providesAuthUseCases(
        auth: FirebaseAuthRepository,
        preferenceRepository: PreferenceRepository
    ): AuthUseCases {
        return AuthUseCases(SignInAnonymous(auth, preferenceRepository), SignOut(auth))
    }


}
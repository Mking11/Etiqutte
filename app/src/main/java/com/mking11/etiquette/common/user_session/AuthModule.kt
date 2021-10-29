package com.mking11.etiquette.common.user_session

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.mking11.etiquette.common.user_session.data.repository.FirebaseAuthRepository
import com.mking11.etiquette.common.user_session.data.repository.PreferenceRepository
import com.mking11.etiquette.common.user_session.domain.AuthUseCases
import com.mking11.etiquette.common.user_session.domain.use_case.SignInAnonymous
import com.mking11.etiquette.common.user_session.domain.use_case.SignOut
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    private const val DEFAULT_PREFERENCES = "Etiquette_Preferences"

    @Provides
    @Singleton
    fun providesPreferenceRepository(
        @ApplicationContext context: Context,
        authFirebaseAuth: FirebaseAuth
    ): PreferenceRepository {
        return PreferenceRepository(
            context.getSharedPreferences(
                DEFAULT_PREFERENCES,
                Context.MODE_PRIVATE
            ),
            authFirebaseAuth
        )
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
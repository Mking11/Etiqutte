package com.mking11.etiquette.common.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mking11.etiquette.BuildConfig
import com.mking11.etiquette.BuildConfig.BUILD_TYPE
import com.mking11.etiquette.common.auth_session.data.repository.FirebaseAuthRepository
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        var realDb: FirebaseDatabase? = null
        if (realDb == null) {
            when (BuildConfig.BUILD_TYPE) {
                "debug" -> {
                    realDb =
                        FirebaseDatabase.getInstance()
                    realDb.setPersistenceEnabled(true)
//                    realDb?.useEmulator(BuildConfig.IP_ADDRESS, 9000)
                }

                else -> {
                    realDb =
                        FirebaseDatabase.getInstance()
                    realDb.setPersistenceEnabled(true)

                }

            }
        }
        return realDb
    }

    @Singleton
    @Provides
    fun provideFirebaseRealDb(database: FirebaseDatabase): FirebaseRealDb {
        return FirebaseRealDb(database)
    }

    @Singleton
    @Provides
    fun providesAuth(): FirebaseAuth {
        return when (BUILD_TYPE) {
            "debug" -> {
                FirebaseAuth.getInstance()
            }
            else -> {
                FirebaseAuth.getInstance()
            }

        }
    }


    @Singleton
    @Provides
    fun providesAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideFirebaseAuthRepo(auth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepository(auth)
    }


}
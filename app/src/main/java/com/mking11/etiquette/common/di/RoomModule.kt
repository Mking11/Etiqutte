package com.mking11.etiquette.common.di

import android.content.Context
import androidx.room.Room
import com.mking11.etiquette.common.EtiquetteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDemoDb(@ApplicationContext context: Context): EtiquetteDatabase =
        Room.databaseBuilder(context, EtiquetteDatabase::class.java, EtiquetteDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

}
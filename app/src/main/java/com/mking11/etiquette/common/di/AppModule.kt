package com.mking11.etiquette.common.di

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import com.mking11.etiquette.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCoil(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context).availableMemoryPercentage(0.25)
            .okHttpClient(
                OkHttpClient.Builder().cache(CoilUtils.createDefaultCache(context))
                    .build()
            ).build()

    }

}
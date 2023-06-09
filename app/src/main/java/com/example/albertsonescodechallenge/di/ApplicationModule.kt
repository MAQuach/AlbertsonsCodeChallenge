package com.example.albertsonescodechallenge.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton

/**
 * Inject application dependencies
 * - Connectivity Manager
 * - Cache
 * - IO Dispatcher
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun providesCacheFile(
        @ApplicationContext context: Context
    ): Cache =
        Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)

    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}

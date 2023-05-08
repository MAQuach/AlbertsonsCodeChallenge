package com.example.albertsonescodechallenge.di

import android.net.ConnectivityManager
import com.example.albertsonescodechallenge.dataLayer.rest.AcronymApi
import com.example.restconnection.services.ServiceCall
import com.example.restconnection.utils.CacheInterceptor
import com.example.restconnection.utils.ForceCacheInterceptor
import com.example.restconnection.utils.NetworkState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Inject network connection components
 * - Acronym Api
 * - Retrofit
 * - Moshi
 * - OkHttpClient
 * - HttpLoggingInterceptor
 * - Cache Interceptor
 * - Force Cache Interceptor
 * - Network State
 * - Service Call
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesAcronymApi(
        retrofit: Retrofit
    ): AcronymApi =
        retrofit.create(AcronymApi::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(AcronymApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(forceCacheInterceptor)
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun providesCacheInterceptor(): CacheInterceptor =
        CacheInterceptor()

    @Provides
    @Singleton
    fun providesForceCacheInterceptor(
        networkState: NetworkState
    ): ForceCacheInterceptor =
        ForceCacheInterceptor(networkState)

    @Provides
    @Singleton
    fun providesNetworkState(
        connectivityManager: ConnectivityManager
    ): NetworkState =
        NetworkState(connectivityManager)

    @Provides
    @Singleton
    fun providesServiceCall(
        networkState: NetworkState
    ): ServiceCall =
        ServiceCall(networkState)
}

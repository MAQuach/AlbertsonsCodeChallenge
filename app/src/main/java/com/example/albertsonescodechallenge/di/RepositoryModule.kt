package com.example.albertsonescodechallenge.di

import com.example.albertsonescodechallenge.dataLayer.rest.NetworkRepository
import com.example.albertsonescodechallenge.dataLayer.rest.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Inject Repository components
 * - Network Repository
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository
}

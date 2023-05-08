package com.example.albertsonescodechallenge.di

import com.example.albertsonescodechallenge.dataLayer.rest.NetworkRepository
import com.example.albertsonescodechallenge.domainLayer.AcronymsUseCases
import com.example.albertsonescodechallenge.domainLayer.GetAcronymSfUseCase
import com.example.restconnection.services.ServiceCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Inject Use Cases
 * - Acronyms Use Cases
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun providesUseCases(
        serviceCall: ServiceCall,
        networkRepository: NetworkRepository
    ): AcronymsUseCases =

        AcronymsUseCases(
            GetAcronymSfUseCase(serviceCall, networkRepository)
        )
}

package com.example.albertsonescodechallenge.domainLayer

import com.example.albertsonescodechallenge.dataLayer.rest.NetworkRepository
import com.example.albertsonescodechallenge.utils.AcronymState
import com.example.restconnection.services.ServiceCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case to get the Acronyms list
 * @param serviceCall defines the method to get the response from the API
 * @param networkRepository defines the call to the API
 */
class GetAcronymSfUseCase @Inject constructor(
    private val serviceCall: ServiceCall,
    private val networkRepository: NetworkRepository
) {

    /**
     * [Function] invoke
     * Before api call, flow should emit loading
     * When api call is success, flow should emit success state with saved response
     * When api call fails, flow should emit error state
     * @param shortForm short form of the acronym
     * @return flow of API response state
     */
    operator fun invoke(shortForm: String): Flow<AcronymState> = flow {
        emit(AcronymState.LOADING)
        serviceCall.serviceCallApi.restServiceCall(
            action = {networkRepository.getLongFormAcronyms(shortForm)},
            success = {emit(AcronymState.SUCCESS(it))},
            error = {emit(AcronymState.ERROR(it))}
        )
    }
}

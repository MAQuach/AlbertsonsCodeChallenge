package com.example.albertsonescodechallenge.dataLayer.rest

import com.example.albertsonescodechallenge.dataLayer.model.AcronymResponseItem
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that implements the network call
 * @param acronymApi Api definition
 */
class NetworkRepositoryImpl @Inject constructor(
    private val acronymApi: AcronymApi
) : NetworkRepository {

    /**
     * [Function] getLongFormAcronyms
     * Retrieve the response from the API
     */
    override suspend fun getLongFormAcronyms(
        shortForm: String
    ): Response<List<AcronymResponseItem>> =
        acronymApi.getAcronyms(shortForm)
}

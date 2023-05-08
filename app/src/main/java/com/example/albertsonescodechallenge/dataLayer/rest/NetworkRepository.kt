package com.example.albertsonescodechallenge.dataLayer.rest

import com.example.albertsonescodechallenge.dataLayer.model.AcronymResponseItem
import retrofit2.Response

/**
 * Interface that defines the function to call the network
 */
interface NetworkRepository {

    /**
     * [Function] getLongFormAcronyms
     * @param shortForm Short form of the acronym
     * @return flow of API response state
     */
    suspend fun getLongFormAcronyms(shortForm: String): Response<List<AcronymResponseItem>>
}

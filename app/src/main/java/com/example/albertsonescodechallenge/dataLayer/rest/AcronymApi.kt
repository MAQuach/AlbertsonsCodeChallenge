package com.example.albertsonescodechallenge.dataLayer.rest

import com.example.albertsonescodechallenge.dataLayer.model.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApi {

    @GET(PATH)
    suspend fun getAcronyms(
        @Query(SHORT_FORM) shortForm: String
    ): Response<List<AcronymResponseItem>>

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/" // API URL
        private const val PATH = "dictionary.py" // API path
        private const val SHORT_FORM = "sf" // API query
    }
}

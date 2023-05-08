package com.example.albertsonescodechallenge.utils

import com.example.albertsonescodechallenge.dataLayer.model.AcronymResponseItem

/**
 * Defines the possible states of the API response
 */
sealed class AcronymState {

    /**
     * Loading state
     */
    object LOADING : AcronymState()

    /**
     * Success state
     * Saves the response object
     */
    data class SUCCESS(val response: List<AcronymResponseItem>) : AcronymState()

    /**
     * Error state
     * Saves the error
     */
    data class ERROR(val error: Exception) : AcronymState()
}

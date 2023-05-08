package com.example.restconnection.services

import com.example.restconnection.utils.NetworkState

class ServiceCall(
    private val networkState: NetworkState
) {
    val serviceCallApi: ServiceCallApi by lazy {
        ServiceCallApiImpl(networkState)
    }
}

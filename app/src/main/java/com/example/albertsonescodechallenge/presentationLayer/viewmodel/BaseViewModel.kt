package com.example.albertsonescodechallenge.presentationLayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

open class BaseViewModel(
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    protected val safeViewModelScope by lazy {
        viewModelScope + ioDispatcher + SupervisorJob() + CoroutineExceptionHandler { _, e ->
            throw Exception(e.localizedMessage)
        }
    }
}

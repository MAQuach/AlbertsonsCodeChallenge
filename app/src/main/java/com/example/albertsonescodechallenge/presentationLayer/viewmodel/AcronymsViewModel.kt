package com.example.albertsonescodechallenge.presentationLayer.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.albertsonescodechallenge.dataLayer.model.Lf
import com.example.albertsonescodechallenge.domainLayer.AcronymsUseCases
import com.example.albertsonescodechallenge.utils.AcronymState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to save all the acronyms data
 * @param acronymsUseCases all possible use cases
 * @param ioDispatcher coroutine dispatcher
 */
@HiltViewModel
class AcronymsViewModel @Inject constructor(
    private val acronymsUseCases: AcronymsUseCases,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _acronymsList: MutableLiveData<AcronymState> = MutableLiveData(AcronymState.LOADING)
    val acronymsList: LiveData<AcronymState> get() = _acronymsList

    var selectedAcronyms: Lf? = null

    /**
     * [Function] getAcronymsSf
     * Calls the Get Acronyms Sf
     * Retrieve a flow of AcronymState
     * Saves the flow to _acronymsList
     */
    fun getAcronymsSf(searchText: String) {
        safeViewModelScope.launch {
            acronymsUseCases.getAcronymSfUseCase(searchText).collect { state ->
                _acronymsList.postValue(state)
            }
        }
    }

    /**
     * [Function] getOnQueryTextListener
     * Creates a listener for the search view
     */
    fun getOnQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    getAcronymsSf(it)
                }
                return true
            }
        }
    }
}

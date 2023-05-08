package com.example.albertsonescodechallenge.domainLayer

import com.example.albertsonescodechallenge.dataLayer.rest.NetworkRepository
import com.example.albertsonescodechallenge.utils.AcronymState
import com.example.restconnection.services.ServiceCall
import com.example.restconnection.utils.NetworkState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAcronymSfUseCaseTest {

    private lateinit var testObject: GetAcronymSfUseCase

    private lateinit var mockServiceCall: ServiceCall
    private val mockNetworkRepository = mockk<NetworkRepository>(relaxed = true)
    private val mockNetworkState = mockk<NetworkState>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockServiceCall = ServiceCall(mockNetworkState)
        testObject = GetAcronymSfUseCase(mockServiceCall, mockNetworkRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `when api has not been called should return loading state`() {
        val states: MutableList<AcronymState> = mutableListOf()
        val job = testScope.launch {
            testObject("asap").toList(states)
        }

        job.cancel()
        assertThat(states[0]).isInstanceOf(AcronymState.LOADING::class.java)
    }

    @Test
    fun `when api call is success return success state with retrieved response`() {
        every { mockNetworkState.isInternetEnabled() } returns true
        coEvery {
            mockNetworkRepository.getLongFormAcronyms("asap")
        } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(
                mockk(),
                mockk(),
                mockk()
            )
        }

        val states: MutableList<AcronymState> = mutableListOf()
        val job = testScope.launch {
            testObject("asap").toList(states)
        }

        job.cancel()
        assertThat((states[1] as AcronymState.SUCCESS).response).hasSize(3)
    }

    @Test
    fun `when api call is error return error state`() {
        every { mockNetworkState.isInternetEnabled() } returns true
        coEvery {
            mockNetworkRepository.getLongFormAcronyms("asap")
        } returns mockk {
            every { isSuccessful } returns false
            every { errorBody().toString() } returns "ERROR"
        }

        val states: MutableList<AcronymState> = mutableListOf()
        val job = testScope.launch {
            testObject("asap").toList(states)
        }

        job.cancel()
        assertThat((states[1] as AcronymState.ERROR).error)
            .hasMessageThat().isEqualTo("ERROR")
    }
}

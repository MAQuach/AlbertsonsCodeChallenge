package com.example.albertsonescodechallenge.presentationLayer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albertsonescodechallenge.domainLayer.AcronymsUseCases
import com.example.albertsonescodechallenge.utils.AcronymState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AcronymsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testObject: AcronymsViewModel

    private val mockUseCases = mockk<AcronymsUseCases>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testObject = AcronymsViewModel(
            mockUseCases,
            testDispatcher
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()

        Dispatchers.resetMain()
    }

    @Test
    fun `update search text when text is inserted`() {
        testObject.getAcronymsSf("This is a test")

        testObject.acronymsList.observeForever {
            // Text should be "This is a test"
            assertThat(it).isEqualTo("This is a test")
        }
    }

    @Test
    fun `when use case returns success state acronymsList should be success state`() {
        every { mockUseCases.getAcronymSfUseCase("asap") } returns flowOf(
            AcronymState.SUCCESS(
                listOf(
                    mockk(relaxed = true),
                    mockk(relaxed = true),
                    mockk(relaxed = true)
                )
            )
        )

        var state: AcronymState = AcronymState.LOADING
        testObject.getAcronymsSf("asap")

        testObject.acronymsList.observeForever {
            state = it
        }

        // List should have 3 elements
        assertThat((state as AcronymState.SUCCESS).response)
            .hasSize(3)
    }

    @Test
    fun `when use case returns error state acronymsList should be error state`() {
        every { mockUseCases.getAcronymSfUseCase("asap") } returns flowOf(
            AcronymState.ERROR(Exception("ERROR"))
        )

        var state: AcronymState = AcronymState.LOADING
        testObject.getAcronymsSf("asap")

        testObject.acronymsList.observeForever {
            state = it
        }

        // Error message should be ERROR
        assertThat((state as AcronymState.ERROR).error)
            .hasMessageThat()
            .isEqualTo("ERROR")
    }
}

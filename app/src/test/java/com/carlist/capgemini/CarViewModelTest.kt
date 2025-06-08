package com.carlist.capgemini

import app.cash.turbine.test
import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.usecase.CarsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CarsViewModel
    private val getCarsUseCase = mockk<CarsUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCars emits Loading then Success`() = runTest {
        val carsList = listOf(CarDataModel( "test",  "Car1", imageUrl = ""))
        coEvery { getCarsUseCase(any()) } returns carsList

        viewModel = CarsViewModel(getCarsUseCase)

        // Start collecting flow
        viewModel.uiState.test {
            // The first emission should be Loading (from init)
            Assert.assertEquals(UiState.Loading, awaitItem())

            // Advance coroutine dispatcher so that launch block executes
            testDispatcher.scheduler.advanceUntilIdle()

            // Next emission should be Success with the cars list
            Assert.assertEquals(UiState.Success(carsList), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchCars emits Loading then Error on exception`() = runTest {
        coEvery { getCarsUseCase(any()) } throws Exception("Failed fetch")

        viewModel = CarsViewModel(getCarsUseCase)

        viewModel.uiState.test {
            Assert.assertEquals(UiState.Loading, awaitItem())

            testDispatcher.scheduler.advanceUntilIdle()

            Assert.assertEquals(UiState.Error(R.string.error_message), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}

package com.krishworld.jetpack_compose_demo.usecase

import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.repository.MainRemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainUseCaseImplTest {
    lateinit var underTest: MainUseCaseImpl

    @RelaxedMockK
    lateinit var mainRemoteRepository: MainRemoteRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        underTest = MainUseCaseImpl(
            mainRemoteRepository = mainRemoteRepository
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `given PagingData,when call method getPhotoPagination, then verify result`() {
        // Given
        val expectedData = PagingData.from(
            listOf(
                Photo("1"),
                Photo("2")
            )
        )

        coEvery { mainRemoteRepository.getPhotoPagination() } returns flowOf(expectedData)


        // When
        val result = runBlocking { underTest.getPhotoPagination().take(1).single() }

        // Then
        assertEquals(expectedData, result)
    }
}
package com.krishworld.jetpack_compose_demo.viewmodel

import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.testrules.CoroutineTestRule
import com.krishworld.jetpack_compose_demo.usecase.dashboard.more.MoreUseCase
import com.krishworld.jetpack_compose_demo.viewmodel.dashboard.more.MoreViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoreViewModelTest {
    private lateinit var underTest: MoreViewModel

    @RelaxedMockK
    lateinit var moreUseCase: MoreUseCase

    /** Required to making Coroutine in unit tests */
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        underTest = MoreViewModel(moreUseCase = moreUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `given PagingData, when call method getPhotoPagination, then verify right method is called`() {
        // Given
        val expectedData = PagingData.from(
            listOf(
                Photo("1"),
                Photo("2")
            )
        )

        every { moreUseCase.getPhotoPagination() } returns flowOf(expectedData)

        // When
        runBlocking { underTest.getPhotoPagination().take(1).single() }

        // Then
        verify(
            verifyBlock = { moreUseCase.getPhotoPagination() },
            exactly = 1
        )
    }
}
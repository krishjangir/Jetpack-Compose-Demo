package com.krishworld.jetpack_compose_demo.data.datasource

import androidx.paging.PagingSource
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import com.krishworld.jetpack_compose_demo.data.remote.repository.MainRemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PhotoSourceTest {

    private lateinit var underTest: PhotoSource

    @RelaxedMockK
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        underTest = PhotoSource(apiService = apiService)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `load should return a Page of Photos when successful`() = runBlocking {
        // Given
        val page = 1
        val pageSize = 10
        val expectedData = listOf(
            Photo("1"),
            Photo("2")
        )
        val loadParams = PagingSource.LoadParams.Refresh(page, pageSize, false)
        coEvery { apiService.getPhotos(page) } returns expectedData

        // When
        val result = underTest.load(loadParams)

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(expectedData, (result as PagingSource.LoadResult.Page).data)
        assertNull(result.prevKey)
        assertEquals(page + 1, result.nextKey)
    }

    @Test
    fun `load should return an Error when there's an exception`() = runBlocking {
        // Given
        val page = 1
        val pageSize = 10
        val loadParams = PagingSource.LoadParams.Refresh(page, pageSize, false)
        val expectedError = Exception("Something went wrong")
        coEvery { apiService.getPhotos(page) } throws expectedError

        // When
        val result = underTest.load(loadParams)

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals(expectedError, (result as PagingSource.LoadResult.Error).throwable)
    }
}
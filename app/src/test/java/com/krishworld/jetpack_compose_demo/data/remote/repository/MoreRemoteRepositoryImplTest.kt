package com.krishworld.jetpack_compose_demo.data.remote.repository

import com.krishworld.jetpack_compose_demo.data.datasource.PhotoSource
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class MoreRemoteRepositoryImplTest {
    private lateinit var underTest: MoreRemoteRepositoryImpl

    @RelaxedMockK
    lateinit var apiService: ApiService

    @RelaxedMockK
    lateinit var photoSource: PhotoSource

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        underTest = MoreRemoteRepositoryImpl(apiService = apiService, photoSource = photoSource)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when call method getPhotos, then verify right method is called`() {
        // When
        runBlocking { underTest.getPhotos(DEFAULT_PAGE_NUMBER) }

        // Then
        coVerify(
            verifyBlock = { apiService.getPhotos(any()) },
            exactly = 1
        )
    }

    @Test
    fun `given empty photo data, when call method getPhotos, then return empty result`() {
        //Given
        coEvery { apiService.getPhotos(any()) } returns emptyList<Photo>()

        // When
        val result = runBlocking { underTest.getPhotos(DEFAULT_PAGE_NUMBER) }

        // Then
        assertEquals(emptyList<Photo>(), result)
    }

    @Test
    fun `given photo data, when call method getPhotos, then return result`() {
        //Given
        val photoData =
            Photo(
                author = "Test",
                width = 100,
                downloadUrl = "Test",
                id = "Test",
                url = "Test",
                height = 100
            )
        coEvery { apiService.getPhotos(any()) } returns arrayListOf(photoData)

        // When
        val result = runBlocking { underTest.getPhotos(DEFAULT_PAGE_NUMBER) }

        // Then
        assertEquals(arrayListOf(photoData), result)
    }

    @Test
    fun `when call method getPhotos throws exception, then check result`() {
        //Given
        coEvery { apiService.getPhotos(any()) }.throws(Exception("Error"))

        // When
        assertThrows(Exception::class.java) {
            runBlocking { underTest.getPhotos(DEFAULT_PAGE_NUMBER) }
        }
    }
}
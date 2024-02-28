package com.krishworld.jetpack_compose_demo.data.remote.repository

import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface MoreRemoteRepository {
    suspend fun getPhotos(page:Int): List<Photo>
    fun getPhotoPagination(): Flow<PagingData<Photo>>
}
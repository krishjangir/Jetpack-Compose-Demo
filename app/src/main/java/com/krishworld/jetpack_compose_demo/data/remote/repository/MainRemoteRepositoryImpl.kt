package com.krishworld.jetpack_compose_demo.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.datasource.PhotoSource
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val photoSource: PhotoSource
) :
    MainRemoteRepository {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return apiService.getPhotos(page)
    }

    override fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(pageSize = 5)) { photoSource }.flow
    }
}
package com.krishworld.jetpack_compose_demo.usecase

import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getPhotoPagination(): Flow<PagingData<Photo>>
}
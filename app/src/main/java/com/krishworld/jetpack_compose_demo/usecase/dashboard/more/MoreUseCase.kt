package com.krishworld.jetpack_compose_demo.usecase.dashboard.more

import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface MoreUseCase {
    fun getPhotoPagination(): Flow<PagingData<Photo>>
}
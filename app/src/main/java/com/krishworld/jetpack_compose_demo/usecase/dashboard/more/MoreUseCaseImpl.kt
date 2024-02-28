package com.krishworld.jetpack_compose_demo.usecase.dashboard.more


import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.repository.MoreRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoreUseCaseImpl @Inject constructor(
    private val mainRemoteRepository: MoreRemoteRepository
) :
    MoreUseCase {
    override fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return mainRemoteRepository.getPhotoPagination()
    }
}
package com.krishworld.jetpack_compose_demo.usecase


import androidx.paging.PagingData
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.repository.MainRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val mainRemoteRepository: MainRemoteRepository
) :
    MainUseCase {
    override fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return mainRemoteRepository.getPhotoPagination()
    }
}
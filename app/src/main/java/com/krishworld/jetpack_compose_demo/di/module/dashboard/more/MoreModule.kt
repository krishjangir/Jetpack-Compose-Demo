package com.krishworld.jetpack_compose_demo.di.module.dashboard.more

import com.krishworld.jetpack_compose_demo.data.datasource.PhotoSource
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import com.krishworld.jetpack_compose_demo.data.remote.repository.MoreRemoteRepositoryImpl
import com.krishworld.jetpack_compose_demo.data.remote.repository.MoreRemoteRepository
import com.krishworld.jetpack_compose_demo.usecase.dashboard.more.MoreUseCase
import com.krishworld.jetpack_compose_demo.usecase.dashboard.more.MoreUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoreModule {

    @Binds
    abstract fun bindMainUseCase(mainUseCaseImpl: MoreUseCaseImpl): MoreUseCase

    @Binds
    abstract fun bindMainRemoteRepository(mainRemoteRepositoryImpl: MoreRemoteRepositoryImpl): MoreRemoteRepository

    companion object {
        @Provides
        @Singleton
        fun bindPhotoSource(apiService: ApiService): PhotoSource {
            return PhotoSource(apiService = apiService)
        }
    }
}
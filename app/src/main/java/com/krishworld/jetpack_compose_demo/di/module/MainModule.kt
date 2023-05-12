package com.krishworld.jetpack_compose_demo.di.module

import com.krishworld.jetpack_compose_demo.data.datasource.PhotoSource
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import com.krishworld.jetpack_compose_demo.data.remote.repository.MainRemoteRepositoryImpl
import com.krishworld.jetpack_compose_demo.data.remote.repository.MainRemoteRepository
import com.krishworld.jetpack_compose_demo.usecase.MainUseCase
import com.krishworld.jetpack_compose_demo.usecase.MainUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindMainUseCase(mainUseCaseImpl: MainUseCaseImpl): MainUseCase

    @Binds
    abstract fun bindMainRemoteRepository(mainRemoteRepositoryImpl: MainRemoteRepositoryImpl): MainRemoteRepository

    companion object {
        @Provides
        @Singleton
        fun bindPhotoSource(apiService: ApiService): PhotoSource {
            return PhotoSource(apiService = apiService)
        }
    }
}
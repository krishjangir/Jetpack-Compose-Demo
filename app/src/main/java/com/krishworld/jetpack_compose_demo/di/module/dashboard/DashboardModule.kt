package com.krishworld.jetpack_compose_demo.di.module.dashboard

import com.krishworld.jetpack_compose_demo.data.local.repository.dashboard.DashboardRepository
import com.krishworld.jetpack_compose_demo.data.local.repository.dashboard.DashboardRepositoryImpl
import com.krishworld.jetpack_compose_demo.usecase.dashboard.DashboardUseCase
import com.krishworld.jetpack_compose_demo.usecase.dashboard.DashboardUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DashboardModule {

    @Binds
    abstract fun bindDashboardUseCase(dashboardUseCaseImpl: DashboardUseCaseImpl): DashboardUseCase

    @Binds
    abstract fun bindDashboardRepository(dashboardRepositoryImpl: DashboardRepositoryImpl): DashboardRepository

    companion object {}
}

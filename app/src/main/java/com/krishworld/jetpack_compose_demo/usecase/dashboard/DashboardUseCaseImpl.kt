package com.krishworld.jetpack_compose_demo.usecase.dashboard

import com.krishworld.jetpack_compose_demo.data.local.repository.dashboard.DashboardRepository
import javax.inject.Inject

class DashboardUseCaseImpl @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : DashboardUseCase {

}
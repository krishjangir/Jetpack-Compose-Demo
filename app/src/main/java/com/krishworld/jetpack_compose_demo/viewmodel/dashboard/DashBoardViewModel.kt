package com.krishworld.jetpack_compose_demo.viewmodel.dashboard

import com.krishworld.jetpack_compose_demo.base.BaseViewModel
import com.krishworld.jetpack_compose_demo.usecase.dashboard.DashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(private val dashboardUseCase: DashboardUseCase) :
    BaseViewModel() {

}
package com.krishworld.jetpack_compose_demo.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krishworld.jetpack_compose_demo.base.BaseViewModel
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainUseCase: MainUseCase) :
    BaseViewModel() {
    fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return mainUseCase.getPhotoPagination().cachedIn(viewModelScope)
    }
}


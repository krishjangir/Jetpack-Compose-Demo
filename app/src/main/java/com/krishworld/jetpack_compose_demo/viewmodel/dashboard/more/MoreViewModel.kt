package com.krishworld.jetpack_compose_demo.viewmodel.dashboard.more

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krishworld.jetpack_compose_demo.base.BaseViewModel
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.usecase.dashboard.more.MoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val moreUseCase: MoreUseCase) :
    BaseViewModel() {
    fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return moreUseCase.getPhotoPagination().cachedIn(viewModelScope)
    }
}


package com.krishworld.jetpack_compose_demo.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krishworld.jetpack_compose_demo.data.model.Photo
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import javax.inject.Inject

class PhotoSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 1
            val photoResponse = apiService.getPhotos(page)
            LoadResult.Page(
                data = photoResponse,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        TODO("Not yet implemented")
    }
}
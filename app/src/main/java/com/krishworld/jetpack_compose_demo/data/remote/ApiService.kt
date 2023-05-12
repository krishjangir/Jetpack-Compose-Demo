package com.krishworld.jetpack_compose_demo.data.remote

import com.krishworld.jetpack_compose_demo.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list")
    suspend fun getPhotos(
        @Query("page") pageNumber: Int,
        @Query("limit") limit: Int = 100
    ): List<Photo>
}
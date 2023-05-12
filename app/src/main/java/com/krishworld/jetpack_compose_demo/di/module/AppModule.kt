package com.krishworld.jetpack_compose_demo.di.module

import android.content.Context
import com.krishworld.jetpack_compose_demo.MainApplication
import com.krishworld.jetpack_compose_demo.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        private const val BASE_URL = "https://picsum.photos/v2/"
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): MainApplication {
        return context as MainApplication
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit:Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
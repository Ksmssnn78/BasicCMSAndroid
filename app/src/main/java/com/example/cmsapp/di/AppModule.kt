package com.example.cmsapp.di

import com.example.cmsapp.Utils.Extensions.MoshiUtil
import com.example.cmsapp.networks.ApiClient
import com.example.cmsapp.networks.api.TodosInterface
import com.example.cmsapp.networks.api.UserInterface
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return MoshiUtil.getMoshi()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return ApiClient.getRetrofit(moshi)
    }

    @Singleton
    @Provides
    fun provideUserApiInterface(retrofit: Retrofit): UserInterface {
        return retrofit.create(UserInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideTodoApiInterface(retrofit: Retrofit): TodosInterface {
        return retrofit.create(TodosInterface::class.java)
    }
}

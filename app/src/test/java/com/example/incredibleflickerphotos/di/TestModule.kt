package com.example.incredibleflickerphotos.di

import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.networking.FlickerService
import com.example.incredibleflickerphotos.util.RemoteServiceProviderTestImpl
import dagger.Module
import dagger.Provides
import io.mockk.mockkClass
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    fun provideFlickerApiClient(): FlickerService = mockkClass(FlickerService::class)

    @Provides
    @Singleton
    fun provideRemoteServiceProvider(): RemoteServiceProvider = RemoteServiceProviderTestImpl()
}
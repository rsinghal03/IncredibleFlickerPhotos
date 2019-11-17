package com.example.incredibleflickerphotos.dependencyinjection

import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.data.RemoteServiceProviderImpl
import com.example.incredibleflickerphotos.networking.FlickerApiClient
import com.example.incredibleflickerphotos.networking.FlickerService
import dagger.Module
import dagger.Provides

@Module
class FlickerPhotoModule {

    @Provides
    fun provideFlickerApiClient(): FlickerService {
        return FlickerApiClient().getRetrofit().create(FlickerService::class.java)
    }

    @Provides
    fun provideRemoteServiceProvider(flickerService: FlickerService): RemoteServiceProvider {
        return RemoteServiceProviderImpl(flickerService)
    }



}
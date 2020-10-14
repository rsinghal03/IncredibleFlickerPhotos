package com.example.incredibleflickerphotos

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.incredibleflickerphotos.dependencyinjection.DaggerFlickerPhotoComponent
import com.example.incredibleflickerphotos.dependencyinjection.FlickerPhotoComponent
import com.example.incredibleflickerphotos.dependencyinjection.FlickerPhotoModule
import timber.log.Timber

var flickerPhotoComponent: FlickerPhotoComponent? = null
class IncredibleFlickerPhotosApplication : Application() {


    init {
        initDagger()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initDagger() {
        flickerPhotoComponent =
            DaggerFlickerPhotoComponent.builder().flickerPhotoModule(FlickerPhotoModule()).build()
    }

    companion object {

        private var instance: IncredibleFlickerPhotosApplication? = null

        fun getInstance(): IncredibleFlickerPhotosApplication =
            instance ?: IncredibleFlickerPhotosApplication().also {
                instance = it
            }

        @VisibleForTesting()
        fun setComponent(appComponent: FlickerPhotoComponent) {
            flickerPhotoComponent = appComponent
        }
    }
}
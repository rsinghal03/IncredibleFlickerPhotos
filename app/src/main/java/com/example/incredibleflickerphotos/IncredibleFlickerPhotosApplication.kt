package com.example.incredibleflickerphotos

import android.app.Application
import com.example.incredibleflickerphotos.dependencyinjection.DaggerFlickerPhotoComponent
import com.example.incredibleflickerphotos.dependencyinjection.FlickerPhotoComponent

class IncredibleFlickerPhotosApplication : Application() {

    lateinit var flickerPhotoComponent: FlickerPhotoComponent

    init {
        initDagger()
    }

    private fun initDagger() {
        flickerPhotoComponent = DaggerFlickerPhotoComponent.create()
    }

    companion object {

        private var instance: IncredibleFlickerPhotosApplication? = null

        fun getInstance(): IncredibleFlickerPhotosApplication =
            instance ?: IncredibleFlickerPhotosApplication().also {
                instance = it
            }
    }
}
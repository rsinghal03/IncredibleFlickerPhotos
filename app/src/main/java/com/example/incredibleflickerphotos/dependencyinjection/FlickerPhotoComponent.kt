package com.example.incredibleflickerphotos.dependencyinjection

import com.example.incredibleflickerphotos.ui.ListOfPhotoFragment
import dagger.Component

@Component(modules = [FlickerPhotoModule::class])
interface FlickerPhotoComponent {

    fun inject(listOfPhotoFragment: ListOfPhotoFragment)
}
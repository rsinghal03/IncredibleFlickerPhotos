package com.example.incredibleflickerphotos.dependencyinjection

import com.example.incredibleflickerphotos.ui.listofphoto.ListOfPhotoFragment
import com.example.incredibleflickerphotos.ui.listofphoto.ListOfPhotoViewModel
import dagger.Component

@Component(modules = [FlickerPhotoModule::class])
interface FlickerPhotoComponent {

    fun inject(listOfPhotoFragment: ListOfPhotoFragment)

    fun inject(viewModel: ListOfPhotoViewModel)
}
package com.example.incredibleflickerphotos.di

import com.example.incredibleflickerphotos.dependencyinjection.FlickerPhotoComponent
import com.example.incredibleflickerphotos.ui.listofphoto.ListOfPhotoViewModelTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent : FlickerPhotoComponent {

    fun inject(viewModel: ListOfPhotoViewModelTest)
}
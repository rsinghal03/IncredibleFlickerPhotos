package com.example.incredibleflickerphotos.ui.listofphoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.incredibleflickerphotos.IncredibleFlickerPhotosApplication
import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.di.DaggerTestComponent
import com.example.incredibleflickerphotos.di.TestComponent
import com.example.incredibleflickerphotos.di.TestModule
import com.example.incredibleflickerphotos.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import javax.inject.Inject

class ListOfPhotoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Inject
    lateinit var remoteServiceProvider: RemoteServiceProvider
    private lateinit var appTestComponent: TestComponent
    private lateinit var viewModel: ListOfPhotoViewModel


    @Before
    fun setUp() {
        RxUnitTestTool.asyncToSync()
        appTestComponent = DaggerTestComponent.builder().testModule(TestModule()).build()
        IncredibleFlickerPhotosApplication.setComponent(appTestComponent)
        appTestComponent.inject(this)
        viewModel = ListOfPhotoViewModel()
    }

    @Test
    fun getListOfPhoto() {
        viewModel.getListOfPhotos()
        remoteServiceProvider.getMetaDataOfPhotosResponse().test().assertNoErrors()
            .assertValue { it.photos.perpage == 100 }
        remoteServiceProvider.getPhotoWithDiffSizeResponse(anyString()).test().assertNoErrors()
            .assertValue(
                remoteServiceProvider.getPhotoWithDiffSizeResponse(anyString()).blockingFirst()
            )
        assertEquals(false, viewModel.loadingState.getOrAwaitValue())
        val expected = remoteServiceProvider.getMetaDataOfPhotosResponse()
            .flatMapIterable { it.photos.photo }
            .flatMap { remoteServiceProvider.getPhotoWithDiffSizeResponse(it.id) }
            .toList()
            .blockingGet()
        assertEquals(expected, viewModel.listOfPhoto.getOrAwaitValue())
    }
}
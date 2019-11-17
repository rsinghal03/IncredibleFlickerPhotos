package com.example.incredibleflickerphotos.ui.listofphoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.incredibleflickerphotos.MockDataProvider
import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListOfPhotoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerLoadingState: Observer<Boolean>

    @Mock
    private lateinit var observerListOfPhoto: Observer<ArrayList<PhotoWithDiffSizeResponse>>

    @Mock
    private lateinit var remoteServiceProvider: RemoteServiceProvider

    private lateinit var viewModel: ListOfPhotoViewModel

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline()}
        RxUnitTestTool.asyncToSync()
        MockitoAnnotations.initMocks(this)
        viewModel = ListOfPhotoViewModel()
        viewModel.getLoadingState().observeForever { observerLoadingState }
        viewModel.getLiveDataOfListOfPhoto().observeForever { observerListOfPhoto }
    }

    @Test
    fun shouldReturnMetaDataOfPhoto() {
        Mockito.`when`(remoteServiceProvider.getMetaDataOfPhotosResponse())
            .thenReturn(Observable.just(MockDataProvider.getMockMetaDataOfPhotos()))
        viewModel.getMetaDataOfPhoto()
        assert(viewModel.getLoadingState().hasActiveObservers())
        assert(viewModel.getLiveDataOfListOfPhoto().hasActiveObservers())
    }
}
package com.example.incredibleflickerphotos.ui.listofphoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incredibleflickerphotos.IncredibleFlickerPhotosApplication
import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListOfPhotoViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<ArrayList<PhotoWithDiffSizeResponse>> = MutableLiveData()

    private val listOfPhotos: ArrayList<PhotoWithDiffSizeResponse> = ArrayList()

    private val compositeDisposable = CompositeDisposable()

    private val loadingState = MutableLiveData<Boolean>()

    @Inject
    lateinit var remoteServiceProvider: RemoteServiceProvider

    init {
        IncredibleFlickerPhotosApplication.getInstance().flickerPhotoComponent.inject(this)
    }

    fun getMetaDataOfPhoto() {
        val disposable = remoteServiceProvider.getMetaDataOfPhotosResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .doOnSubscribe {loadingState.postValue(true)}
            .flatMap {
                Observable.fromIterable(it.photos.photo)
            }
            .subscribe({
                getPhotoWithDiffSize(it.id)
//                Log.i("Viewmodel", "onNext")
            }, {
//                Log.i("Viewmodel", "MetaDataOnErrorCalled")
            }, {
//                Log.i("Viewmodel", "onComplete")
                loadingState.postValue(false)
            })
            .addTo(compositeDisposable)
    }

    private fun getPhotoWithDiffSize(photoId: String) {
        remoteServiceProvider.getPhotoWithDiffSizeResponse(photoId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
//                Log.i("Viewmodel", "onSuccess")
                listOfPhotos.add(it)
                mutableLiveData.postValue(listOfPhotos)
            }, {
//                Log.i("Viewmodel", "onErrorDiffSizePhoto")
            })
            .addTo(compositeDisposable)
    }

     fun getLiveDataOfListOfPhoto(): LiveData<ArrayList<PhotoWithDiffSizeResponse>> {
        return mutableLiveData
    }

    fun getLoadingState(): LiveData<Boolean> = loadingState

    override fun onCleared() {
        super.onCleared()
    }
}
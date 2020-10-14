package com.example.incredibleflickerphotos.ui.listofphoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import com.example.incredibleflickerphotos.flickerPhotoComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * List of photo view model
 *
 * @constructor Create empty List of photo view model
 */
class ListOfPhotoViewModel : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _listOfPhoto = MutableLiveData<List<PhotoWithDiffSizeResponse>>()
    val listOfPhoto: LiveData<List<PhotoWithDiffSizeResponse>> = _listOfPhoto

    private val _failureEvent = MutableLiveData<String>()
    val failureEvent: LiveData<String> = _failureEvent

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var remoteServiceProvider: RemoteServiceProvider

    init {
        flickerPhotoComponent?.inject(this)
    }

    /**
     * Make chained api call to get the list of photos
     *
     */
    fun getListOfPhotos() {
        compositeDisposable.add(remoteServiceProvider.getMetaDataOfPhotosResponse()
            .doOnSubscribe { _loadingState.postValue(true) }
            .flatMapIterable { metaDataOfPhotosResponse -> metaDataOfPhotosResponse.photos.photo }
            .flatMap { photo -> remoteServiceProvider.getPhotoWithDiffSizeResponse(photo.id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { _loadingState.postValue(false) }
            .toList()
            .doOnError { _loadingState.postValue(false) }
            .subscribe(
                { listOfPhoto -> this._listOfPhoto.value = (listOfPhoto) },
                { _failureEvent.value = it.message })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
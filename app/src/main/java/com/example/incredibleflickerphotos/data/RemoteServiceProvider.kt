package com.example.incredibleflickerphotos.data

import com.example.incredibleflickerphotos.data.model.MetaDataOfPhotosResponse
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteServiceProvider {

    fun getMetaDataOfPhotosResponse(): Observable<MetaDataOfPhotosResponse>

    fun getPhotoWithDiffSizeResponse(photoId: String): Single<PhotoWithDiffSizeResponse>

}
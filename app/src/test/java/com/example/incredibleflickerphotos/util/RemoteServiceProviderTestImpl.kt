package com.example.incredibleflickerphotos.util

import com.example.incredibleflickerphotos.data.RemoteServiceProvider
import com.example.incredibleflickerphotos.data.model.MetaDataOfPhotosResponse
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import io.reactivex.Observable

class RemoteServiceProviderTestImpl : RemoteServiceProvider {
    override fun getMetaDataOfPhotosResponse(): Observable<MetaDataOfPhotosResponse> {
        return MockDataProvider.getMockMetaDataOfPhotos()
    }

    override fun getPhotoWithDiffSizeResponse(photoId: String): Observable<PhotoWithDiffSizeResponse> {
        return MockDataProvider.getMockPhotoWithDiffSize()
    }
}
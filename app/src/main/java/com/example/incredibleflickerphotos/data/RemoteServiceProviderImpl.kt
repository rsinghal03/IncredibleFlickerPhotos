package com.example.incredibleflickerphotos.data

import com.example.incredibleflickerphotos.data.model.MetaDataOfPhotosResponse
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import com.example.incredibleflickerphotos.networking.FlickerService
import io.reactivex.Observable
import io.reactivex.Single

class RemoteServiceProviderImpl(private val flickerService: FlickerService) : RemoteServiceProvider {

    companion object {
        const val METHOD_PUBLIC_PHOTOS = "flickr.people.getPublicPhotos"
        const val METHOD_PHOTOS_SIZES = "flickr.photos.getSizes"
        const val API_KEY = "227be805b3d6e934926d049533bb938a"
        const val USER_ID = "135487628%40N06"
        const val NO_JSON_CALLBACK = "1"
        const val FORMAT = "json"
    }

    override fun getMetaDataOfPhotosResponse(): Observable<MetaDataOfPhotosResponse> {
        return flickerService.getMetaDataOfPhotosResponse(METHOD_PUBLIC_PHOTOS,
            FORMAT, API_KEY, NO_JSON_CALLBACK)
    }

    override fun getPhotoWithDiffSizeResponse(photoId: String): Observable<PhotoWithDiffSizeResponse> {
        return flickerService.getPhotoWithDiffSizeResponse(
            FORMAT, photoId, METHOD_PHOTOS_SIZES, API_KEY, USER_ID, NO_JSON_CALLBACK)
    }

}
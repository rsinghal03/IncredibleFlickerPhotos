package com.example.incredibleflickerphotos.networking

import com.example.incredibleflickerphotos.data.model.MetaDataOfPhotosResponse
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerService {

    @GET("services/rest/")
    fun getFlickerPhotoResponse(@Query("method") method: String,
                                @Query("format") format: String,
                                @Query("api_key") apiKey: String,
                                @Query("user_id") userId: String,
                                @Query("nojsoncallback") noJsonCallback: String): Observable<MetaDataOfPhotosResponse>

    @GET("services/rest/")
    fun getPhotoWithDiffSizeResponse(@Query("format") format: String,
                                     @Query("photo_id") photoId: String,
                                     @Query("method") method: String,
                                     @Query("api_key") apiKey: String,
                                     @Query("user_id") userId: String,
                                     @Query("nojsoncallback") noJsonCallback: String): Observable<PhotoWithDiffSizeResponse>


}
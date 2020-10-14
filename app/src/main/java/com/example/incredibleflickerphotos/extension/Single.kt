package com.example.incredibleflickerphotos.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single

fun <T> Single<T>.asLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher<T>(this.toFlowable())
}
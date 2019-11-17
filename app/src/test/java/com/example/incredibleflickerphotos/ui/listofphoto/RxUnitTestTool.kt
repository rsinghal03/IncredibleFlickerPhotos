package com.example.incredibleflickerphotos.ui.listofphoto

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class RxUnitTestTool {
    companion object {
        fun asyncToSync() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
            RxAndroidPlugins.setInitMainThreadSchedulerHandler {Schedulers.trampoline()}
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        }
    }
}
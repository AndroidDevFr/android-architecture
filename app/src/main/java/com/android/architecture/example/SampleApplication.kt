package com.android.architecture.example

import android.support.multidex.MultiDexApplication
import timber.log.Timber

open class SampleApplication: MultiDexApplication() {

    private val component: SampleApplicationComponent by lazy {
        DaggerSampleApplicationComponent.builder()
                .sampleApplicationModule(SampleApplicationModule(applicationContext, this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    fun component(): SampleApplicationComponent {
        return component
    }

}
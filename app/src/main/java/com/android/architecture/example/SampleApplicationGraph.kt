package com.android.architecture.example

import com.android.architecture.example.lib.Environment


interface SampleApplicationGraph {

    fun environment(): Environment
    fun inject(sampleApplication: SampleApplication)

}
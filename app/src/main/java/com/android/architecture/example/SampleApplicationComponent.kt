package com.android.architecture.example

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SampleApplicationModule::class])
interface SampleApplicationComponent : SampleApplicationGraph
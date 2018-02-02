package com.android.architecture.example;

import android.app.Application;
import android.content.Context;

import com.android.architecture.example.core.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = NetworkModule.class)
public class SampleApplicationModule {

    private final Application application;

    public SampleApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

}
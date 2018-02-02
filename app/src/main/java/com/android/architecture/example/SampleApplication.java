package com.android.architecture.example;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class SampleApplication extends Application{

    private SampleApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerSampleApplicationComponent.builder()
                .sampleApplicationModule(new SampleApplicationModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public SampleApplicationComponent getComponent() {
        return component;
    }

    public static SampleApplicationComponent component(Context context) {
        return ((SampleApplication) context.getApplicationContext()).getComponent();
    }
}
package com.android.architecture.example;

import com.android.architecture.example.ui.GithubFragment;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = SampleApplicationModule.class)
public interface SampleApplicationComponent {

    void inject(GithubFragment githubFragment);
}
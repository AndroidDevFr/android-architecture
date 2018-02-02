package com.android.architecture.example.core.repository;

import com.android.architecture.example.core.api.GithubAPI;
import com.android.architecture.example.core.model.GithubUser;

import io.reactivex.Single;

public class GithubRepositoryImpl implements GithubRepository {

    private final GithubAPI githubAPI;

    public GithubRepositoryImpl(GithubAPI githubAPI) {
        this.githubAPI = githubAPI;
    }

    @Override
    public Single<GithubUser> getUserProfile(String name) {
        return githubAPI.user(name);
    }
}

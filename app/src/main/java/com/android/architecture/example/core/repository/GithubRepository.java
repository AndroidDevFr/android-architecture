package com.android.architecture.example.core.repository;

import com.android.architecture.example.core.model.GithubUser;

import io.reactivex.Single;

public interface GithubRepository {
    Single<GithubUser> getUserProfile(String name);
}

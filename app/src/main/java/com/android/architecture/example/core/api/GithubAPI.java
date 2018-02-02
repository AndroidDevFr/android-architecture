package com.android.architecture.example.core.api;

import com.android.architecture.example.core.model.GithubUser;

import io.reactivex.Single;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GithubAPI {

    @Headers("Cache-Control: max-age=640000")
    @GET("/users/{user}")
    Single<GithubUser> user(@Path("user") String user);

}

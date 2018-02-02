package com.android.architecture.example.core;

import com.android.architecture.example.core.api.GithubAPI;
import com.android.architecture.example.core.repository.GithubRepository;
import com.android.architecture.example.core.repository.GithubRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final Request request = chain.request();

                    final Request newRequest = request.newBuilder()
                            //ajoute "baerer: 1234567890" en header de chaque requête
                            .addHeader("bearer","1234567890")
                            .build();

                    return chain.proceed(newRequest);
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Singleton
    @Provides
    public GithubAPI provideGithubApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GithubAPI.class);
    }

    @Singleton
    @Provides
    public GithubRepository provideGithubRepository(GithubAPI githubAPI){
        return new GithubRepositoryImpl(githubAPI);
    }
}

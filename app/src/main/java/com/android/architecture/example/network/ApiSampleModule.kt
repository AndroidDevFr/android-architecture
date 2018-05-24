package com.android.architecture.example.network

import com.android.architecture.example.BuildConfig
import com.android.architecture.example.lib.Build
import com.android.architecture.example.lib.CurrentUserType
import com.android.architecture.example.lib.qualifiers.ApiSampleOkHttpClient
import com.android.architecture.example.lib.qualifiers.ApiSampleRetrofit
import com.android.architecture.example.network.interceptors.ApiSampleRequestInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class ApiSampleModule {

    @Provides
    @Singleton
    fun provideApiSampleRequestInterceptor(build: Build, locale: Locale, currentUser: CurrentUserType): ApiSampleRequestInterceptor =
            ApiSampleRequestInterceptor(build, locale, currentUser)

    @Provides
    @Singleton
    @ApiSampleOkHttpClient
    fun provideOkHttpClient(apiSampleRequestInterceptor: ApiSampleRequestInterceptor,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(apiSampleRequestInterceptor)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }

        return client.build()
    }

    @Provides
    @Singleton
    @ApiSampleRetrofit
    fun provideApiSampleRetrofit(@ApiSampleOkHttpClient okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            createRetrofit(ApiEndpoint.SAMPLE.url(), okHttpClient, moshi)

    private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApiSampleService(@ApiSampleRetrofit retrofit: Retrofit): ApiSampleService =
            retrofit.create(ApiSampleService::class.java)

    @Provides
    @Singleton
    fun provideApiSampleType(apiSampleService: ApiSampleService, moshi: Moshi): ApiSampleType = ApiSample(apiSampleService, moshi)

}
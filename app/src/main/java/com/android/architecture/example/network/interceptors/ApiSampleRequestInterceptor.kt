package com.android.architecture.example.network.interceptors

import com.android.architecture.example.lib.Build
import com.android.architecture.example.lib.CurrentUserType
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

private const val ANDROID_DEVICE_TYPE_STRING = "androidphone"

class ApiSampleRequestInterceptor(
        private val build: Build,
        private val locale: Locale,
        private val currentUser: CurrentUserType
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(request(chain.request()))
    }

    private fun request(initialRequest: Request): Request {
        val requestBuilder = initialRequest.newBuilder()
                .header("User-Agent", ANDROID_DEVICE_TYPE_STRING)
                .header("X-Agent-Version", build.versionCode().toString())
                .header("Accept", "application/json")
                .header("Accept-Language", locale.language)

        if (currentUser.getAccessToken() != null) {
            requestBuilder.addHeader("Authorization", requireNotNull(currentUser.getAccessToken()))
        }

        return requestBuilder.build()
    }

}
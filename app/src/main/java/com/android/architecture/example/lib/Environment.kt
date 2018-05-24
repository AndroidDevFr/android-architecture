package com.android.architecture.example.lib

import com.android.architecture.example.network.ApiSampleType
import com.squareup.moshi.Moshi
import io.reactivex.Scheduler


class Environment(
        var moshi: Moshi,
        var currentUser: CurrentUserType,
        var apiSample: ApiSampleType,
        var scheduler: Scheduler
)
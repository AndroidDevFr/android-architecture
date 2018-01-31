package com.android.architecture.example.lib

import com.android.architecture.example.network.ApiSampleType
import com.google.gson.Gson
import io.reactivex.Scheduler


class Environment(
        var gson: Gson,
        var currentUser: CurrentUserType,
        var apiSample: ApiSampleType,
        var scheduler: Scheduler
)
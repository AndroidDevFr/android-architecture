package com.android.architecture.example.network

import com.android.architecture.example.models.User
import io.reactivex.Observable


abstract class ApiSampleType {

    abstract fun fetchUsers(): Observable<List<User>>

}
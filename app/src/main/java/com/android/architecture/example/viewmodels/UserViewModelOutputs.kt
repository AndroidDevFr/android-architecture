package com.android.architecture.example.viewmodels

import com.android.architecture.example.models.User
import io.reactivex.Observable


interface UserViewModelOutputs {

    fun users(): Observable<List<User>>

}
package com.android.architecture.example.viewmodels

import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import io.reactivex.Observable


interface UserViewModelErrors {

    fun fetchUsersError(): Observable<ErrorEnvelope>

}
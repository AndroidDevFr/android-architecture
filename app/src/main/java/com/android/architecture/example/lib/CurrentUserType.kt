package com.android.architecture.example.lib

import com.android.architecture.example.models.User
import io.reactivex.Observable


abstract class CurrentUserType {

    abstract fun login(newUser: User, accessToken: String)

    abstract fun logout()

    abstract fun refresh(freshUser: User)

    abstract fun refresh(accessToken: String)

    abstract fun toObservable(): Observable<User>

    abstract fun getAccessToken(): String?

    abstract fun getUser(): User?

}
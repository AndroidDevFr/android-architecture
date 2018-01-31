package com.android.architecture.example.lib

import com.android.architecture.example.lib.preferences.StringPreferenceType
import com.android.architecture.example.lib.qualifiers.AccessTokenPreference
import com.android.architecture.example.models.User
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class CurrentUser(
        @AccessTokenPreference private val accessTokenPreference: StringPreferenceType
) : CurrentUserType() {

    private val user = BehaviorSubject.create<User>()

    init {

    }

    override fun login(newUser: User, accessToken: String) {
        accessTokenPreference.set(accessToken)
        user.onNext(newUser)
    }

    override fun logout() {
        accessTokenPreference.delete()
        user.onNext(User())
    }

    override fun refresh(freshUser: User) {
        user.onNext(freshUser)
    }

    override fun refresh(accessToken: String) {
        accessTokenPreference.set(accessToken)
    }

    override fun toObservable(): Observable<User> = user

    override fun getUser(): User? = user.value

    override fun getAccessToken(): String? = accessTokenPreference.get()

}
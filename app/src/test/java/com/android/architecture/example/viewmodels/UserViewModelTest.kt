package com.android.architecture.example.viewmodels

import com.android.architecture.example.SampleRobolectricTestCase
import com.android.architecture.example.models.User
import com.android.architecture.example.models.UserFactory
import com.android.architecture.example.network.MockApiSample
import io.reactivex.Observable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test


class UserViewModelTest : SampleRobolectricTestCase() {

    @Test
    fun testInitUsers() {
        val environment = environment()

        val apiSample = object : MockApiSample() {
            override fun fetchUsers(): Observable<List<User>> = Observable.just(arrayListOf(UserFactory.creator()))
        }
        environment.apiSample = apiSample

        val vm = UserViewModel(environment, scopeProvider())
        val users = TestSubscriber<List<User>>()
        vm.outputs.users().subscribe(users::onNext)

        vm.inputs.fetchNext()
        users.assertValueCount(1)
    }

}
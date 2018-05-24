package com.android.architecture.example.viewmodels

import com.android.architecture.example.lib.ActivityViewModel
import com.android.architecture.example.lib.Environment
import com.android.architecture.example.lib.rx.Irrelevant
import com.android.architecture.example.lib.rx.transformers.Transformers.neverError
import com.android.architecture.example.lib.rx.transformers.Transformers.pipeApiErrorsTo
import com.android.architecture.example.models.User
import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


class UserViewModel(
        environment: Environment,
        scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), UserViewModelInputs, UserViewModelOutputs, UserViewModelErrors {

    private val initViews = PublishSubject.create<Irrelevant>()
    private val fetchUsersError = PublishSubject.create<ErrorEnvelope>()
    private val users = PublishSubject.create<List<User>>()

    private val apiSample = environment.apiSample

    val inputs: UserViewModelInputs = this
    val outputs: UserViewModelOutputs = this
    val errors: UserViewModelErrors = this

    init {
        initViews
                .switchMap { this.fetchUsers() }
                .autoDisposable(scopeProvider)
                .subscribe(users::onNext)

        fetchUsersError
                .autoDisposable(scopeProvider)
                .subscribe { Timber.e("Fetch users error: %s", it.error) }
    }

    // INPUTS

    override fun fetchNext() {
        initViews.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun users(): Observable<List<User>> = users

    // ERRORS

    override fun fetchUsersError(): Observable<ErrorEnvelope> = fetchUsersError

    private fun fetchUsers(): Observable<List<User>> {
        return apiSample.fetchUsers()
                .compose(pipeApiErrorsTo(fetchUsersError))
                .compose(neverError())
    }

}
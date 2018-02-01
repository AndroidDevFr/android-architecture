package com.android.architecture.example.ui

import android.os.Bundle
import com.android.architecture.example.R
import com.android.architecture.example.lib.BaseActivity
import com.android.architecture.example.lib.Environment
import com.android.architecture.example.lib.rx.transformers.Transformers.observeForUI
import com.android.architecture.example.lib.utils.TransitionUtils.exit
import com.android.architecture.example.models.User
import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import com.android.architecture.example.viewmodels.UserViewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import timber.log.Timber

class UserActivity : BaseActivity<UserViewModel>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        attachViewModel(fun(env: Environment, scp: AndroidLifecycleScopeProvider): UserViewModel = UserViewModel(env, scp))
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)

        viewModel.outputs.users()
                .compose(observeForUI())
                .autoDisposable(scopeProvider)
                .subscribe(this::showUsers)

        viewModel.errors.fetchUsersError()
                .compose(observeForUI())
                .autoDisposable(scopeProvider)
                .subscribe(this::showError)

        viewModel.inputs.initViews()
    }

    override fun exitTransition(): Pair<Int, Int>? = exit()

    private fun showError(errorEnvelope: ErrorEnvelope) {
        Timber.d(errorEnvelope.error)
    }

    private fun showUsers(users: List<User>) {
        users.forEach {
            Timber.d("User: ${it.name}")
        }
    }

}
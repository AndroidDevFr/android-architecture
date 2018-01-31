package com.android.architecture.example.lib

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.android.architecture.example.SampleApplication
import com.android.architecture.example.SampleApplicationComponent
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

typealias ViewModelConstructor = (Environment, AndroidLifecycleScopeProvider) -> ActivityViewModel

open class BaseActivity<out ViewModelType : ActivityViewModel>(private val viewModelSupplier: ViewModelConstructor) : AppCompatActivity() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    protected val viewModel: ViewModelType by lazy { viewModelSupplier(environment(), scopeProvider) as ViewModelType }

    private val back = PublishSubject.create<Boolean>()

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        viewModel.activityResult(ActivityResult.create(requestCode, resultCode, intent))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("onCreate %s", this.toString())
        viewModel.onCreate(this, savedInstanceState)
    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.intent(intent)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Timber.v("onStart %s", this.toString())
        back
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_STOP))
                .subscribe { _ -> goBack() }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.v("onResume %s", this.toString())
        viewModel.onResume(this)
    }

    @CallSuper
    override fun onPause() {
        Timber.v("onPause %s", this.toString())
        viewModel.onPause()
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        Timber.v("onStop %s", this.toString())
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v("onDestroy %s", this.toString())
        viewModel.onDestroy()
        super.onDestroy()
    }

    protected open fun exitTransition(): Pair<Int, Int>? {
        return null
    }

    protected fun application(): SampleApplication = application as SampleApplication

    protected fun component(): SampleApplicationComponent = application().component()

    protected fun environment(): Environment = component().environment()

    private fun goBack() {
        super.onBackPressed()
        val exitTransitions = exitTransition()
        if (exitTransitions != null) {
            overridePendingTransition(exitTransitions.first, exitTransitions.second)
        }
    }

}
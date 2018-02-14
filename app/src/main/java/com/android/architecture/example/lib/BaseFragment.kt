package com.android.architecture.example.lib

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.android.architecture.example.lib.utils.BundleUtils
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import timber.log.Timber

typealias FragmentViewModelConstructor = (Environment, AndroidLifecycleScopeProvider) -> FragmentViewModel

private const val VIEW_MODEL_KEY = "viewModel"

class BaseFragment<ViewModelType : FragmentViewModel> : Fragment() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    protected lateinit var viewModel: ViewModelType

    protected fun attachViewModel(activity: AppCompatActivity, viewModelSupplier: FragmentViewModelConstructor, savedInstanceState: Bundle?) {
        viewModel = FragmentViewModelManager.fetch(activity, scopeProvider, viewModelSupplier, BundleUtils.maybeGetBundle(savedInstanceState, VIEW_MODEL_KEY))
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        viewModel.activityResult(ActivityResult.create(requestCode, resultCode, intent))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("onCreate %s", this.toString())
        viewModel.arguments(arguments)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.v("onResume %s", this.toString())
        viewModel.onResume(this)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.v("onPause %s", this.toString())
        viewModel.onPause()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.v("onDestroyView %s", this.toString())
        FragmentViewModelManager.destroy(viewModel)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {

        var viewModelEnvelope = Bundle()
        viewModelEnvelope = FragmentViewModelManager.save(viewModel, viewModelEnvelope)

        outState.putBundle(VIEW_MODEL_KEY, viewModelEnvelope)

        super.onSaveInstanceState(outState)
    }

}
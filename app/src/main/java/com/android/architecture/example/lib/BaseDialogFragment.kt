package com.android.architecture.example.lib

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.architecture.example.lib.utils.BundleUtils
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import timber.log.Timber


typealias DialogFragmentViewModelConstructor = (Environment, AndroidLifecycleScopeProvider) -> FragmentViewModel

private const val VIEW_MODEL_KEY = "viewModel"

open class BaseDialogFragment<ViewModelType : FragmentViewModel> : DialogFragment() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    protected lateinit var viewModel: ViewModelType

    protected fun attachViewModel(viewModelSupplier: DialogFragmentViewModelConstructor, savedInstanceState: Bundle?) {
        viewModel = FragmentViewModelManager.fetch(context, scopeProvider, viewModelSupplier, BundleUtils.maybeGetBundle(savedInstanceState, VIEW_MODEL_KEY))
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        Timber.v("onCreateView %s", this.toString())
        viewModel.arguments(arguments)
        return view
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
    override fun onDestroy() {
        super.onDestroy()
        Timber.v("onDestroy %s", this.toString())
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        Timber.v("onDetach %s", this.toString())
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        Timber.v("onSaveInstanceState %s", this.toString())

        var viewModelEnvelope = Bundle()
        viewModelEnvelope = FragmentViewModelManager.save(viewModel, viewModelEnvelope)

        outState.putBundle(VIEW_MODEL_KEY, viewModelEnvelope)

        super.onSaveInstanceState(outState)
    }

}
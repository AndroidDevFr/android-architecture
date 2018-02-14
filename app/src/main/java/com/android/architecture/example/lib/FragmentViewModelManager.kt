package com.android.architecture.example.lib

import android.content.Context
import android.os.Bundle
import com.android.architecture.example.SampleApplication
import com.android.architecture.example.lib.utils.BundleUtils
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import java.util.*

private const val VIEW_MODEL_ID_KEY = "fragment_view_model_id"
private const val VIEW_MODEL_STATE_KEY = "fragment_view_model_state"

object FragmentViewModelManager {

    private val viewModels = HashMap<String, FragmentViewModel>()

    fun <ViewModelType : FragmentViewModel> fetch(context: Context,
                                                  scopeProvider: AndroidLifecycleScopeProvider,
                                                  fragmentViewModelConstructor: FragmentViewModelConstructor,
                                                  savedInstanceState: Bundle?): ViewModelType {

        val viewModelId = fetchId(savedInstanceState)
        var fragmentViewModel: FragmentViewModel? = viewModels[viewModelId]

        if (fragmentViewModel == null) {
            fragmentViewModel = create(context, scopeProvider, fragmentViewModelConstructor, savedInstanceState, viewModelId)
        }

        return fragmentViewModel as ViewModelType
    }

    fun save(fragmentViewModel: FragmentViewModel, envelope: Bundle) : Bundle {
        envelope.putString(VIEW_MODEL_ID_KEY, findIdForViewModel(fragmentViewModel))

        val state = Bundle()
        envelope.putBundle(VIEW_MODEL_STATE_KEY, state)

        return envelope
    }

    fun destroy(fragmentViewModel: FragmentViewModel) {
        fragmentViewModel.onDestroyView()

        val iterator = viewModels.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (fragmentViewModel == entry.value) {
                iterator.remove()
            }
        }
    }

    private fun <ViewModelType : FragmentViewModel> create(context: Context,
                                                           scopeProvider: AndroidLifecycleScopeProvider,
                                                           fragmentViewModelConstructor: FragmentViewModelConstructor,
                                                           savedInstanceState: Bundle?,
                                                           viewModelId: String): ViewModelType {

        val application = context.applicationContext as SampleApplication
        val environment = application.component().environment()

        val viewModel = fragmentViewModelConstructor(environment, scopeProvider)

        viewModels[viewModelId] = viewModel

        viewModel.onCreate(context, BundleUtils.maybeGetBundle(savedInstanceState, VIEW_MODEL_STATE_KEY))

        return viewModel as ViewModelType
    }

    private fun fetchId(savedInstanceState: Bundle?): String {
        return if (savedInstanceState != null) savedInstanceState.getString(VIEW_MODEL_ID_KEY) else UUID.randomUUID().toString()
    }

    private fun findIdForViewModel(fragmentViewModel: FragmentViewModel): String {
        for ((key, value) in viewModels) {
            if (fragmentViewModel == value) {
                return key
            }
        }
        throw RuntimeException("Cannot find view model in map!")
    }

}
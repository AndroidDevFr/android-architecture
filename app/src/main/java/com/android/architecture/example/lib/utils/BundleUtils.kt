package com.android.architecture.example.lib.utils

import android.os.Bundle


object BundleUtils {

    fun maybeGetBundle(state: Bundle?, key: String): Bundle? {
        return state?.getBundle(key)
    }

}
package com.android.architecture.example

import android.content.Context
import com.android.architecture.example.lib.Environment
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.android.lifecycle.test.TestLifecycleOwner
import junit.framework.TestCase
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.multidex.ShadowMultiDex
import java.lang.Exception

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, shadows = [ShadowMultiDex::class], sdk = [16])
abstract class SampleRobolectricTestCase : TestCase() {

    private var application: SampleApplication? = null
    private var environment: Environment? = null

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()

        environment = application()?.component()?.environment()
    }

    private fun application(): SampleApplication? {
        if (application != null) {
            return application
        }

        application = RuntimeEnvironment.application as SampleApplication
        return application
    }

    protected fun context(): Context? {
        return application()?.applicationContext
    }

    protected fun environment(): Environment {
        return environment!!
    }

    protected fun scopeProvider(): AndroidLifecycleScopeProvider {
        val testLifecycleOwner = TestLifecycleOwner.create()
        return AndroidLifecycleScopeProvider.from(testLifecycleOwner)
    }

}
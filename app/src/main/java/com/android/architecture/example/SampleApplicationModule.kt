package com.android.architecture.example

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.preference.PreferenceManager
import com.android.architecture.example.lib.Build
import com.android.architecture.example.lib.CurrentUser
import com.android.architecture.example.lib.CurrentUserType
import com.android.architecture.example.lib.Environment
import com.android.architecture.example.lib.preferences.PreferenceModule
import com.android.architecture.example.lib.preferences.StringPreferenceType
import com.android.architecture.example.lib.qualifiers.AccessTokenPreference
import com.android.architecture.example.lib.qualifiers.ApplicationContext
import com.android.architecture.example.network.ApiSampleType
import com.android.architecture.example.network.NetworkModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, PreferenceModule::class])
class SampleApplicationModule(
        private val context: Context,
        private val application: Application
) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun providePackageInfo(application: Application): PackageInfo =
            application.packageManager.getPackageInfo(application.packageName, 0)

    @Provides
    @Singleton
    fun provideBuild(packageInfo: PackageInfo): Build = Build(packageInfo)

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    fun provideEnvironment(gson: Gson, currentUser: CurrentUserType, apiSample: ApiSampleType, scheduler: Scheduler): Environment {
        return Environment(gson, currentUser, apiSample, scheduler)
    }

    @Provides
    @Singleton
    fun provideCurrentUser(@AccessTokenPreference accessTokenPreference: StringPreferenceType): CurrentUserType =
            CurrentUser(accessTokenPreference)

    @Provides
    @Singleton
    fun provideScheduler(): Scheduler = Schedulers.computation()

}
package com.android.architecture.example.lib.preferences

import android.content.SharedPreferences
import com.android.architecture.example.lib.qualifiers.AccessTokenPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    @AccessTokenPreference
    fun provideAccessTokenPreference(sharedPreferences: SharedPreferences): StringPreferenceType =
            StringPreference(sharedPreferences, SharedPreferenceKey.ACCESS_TOKEN)

}
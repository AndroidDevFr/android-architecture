/*
 * Copyright 2014 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ***
 *
 * Original: https://github.com/JakeWharton/u2020/blob/7363d27ee0356e24dcbd00dc6926d993ee56d6e2/src/main/java/com/jakewharton/u2020/data/prefs/BooleanPreference.java
 * Modifications: Some modifiers and annotations have been added by Guillaume Mas.
 */
package com.android.architecture.example.lib.preferences

import android.content.SharedPreferences

class BooleanPreference(private val sharedPreferences: SharedPreferences, private val key: String, private val defaultValue: Boolean) : BooleanPreferenceType {

    constructor(sharedPreferences: SharedPreferences, key: String) : this(sharedPreferences, key, false)

    override fun get(): Boolean = sharedPreferences.getBoolean(key, defaultValue)

    override fun isSet(): Boolean = sharedPreferences.contains(key)

    override fun set(value: Boolean) = sharedPreferences.edit().putBoolean(key, value).apply()

    override fun delete() = sharedPreferences.edit().remove(key).apply()

}
package com.android.architecture.example.lib.preferences

interface BooleanPreferenceType {

    /**
     * Get the current value of the preference.
     */
    fun get(): Boolean

    /**
     * Returns whether a value has been explicitly set for the preference.
     */
    fun isSet(): Boolean

    /**
     * Set the preference to a value.
     */
    fun set(value: Boolean)

    /**
     * Delete the currently stored preference.
     */
    fun delete()

}
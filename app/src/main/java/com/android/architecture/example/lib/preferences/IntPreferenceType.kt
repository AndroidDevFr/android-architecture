package com.android.architecture.example.lib.preferences

interface IntPreferenceType {

    /**
     * Get the current value of the preference.
     */
    fun get(): Int

    /**
     * Returns whether a value has been explicitly set for the preference.
     */
    fun isSet(): Boolean

    /**
     * Set the preference to a value.
     */
    fun set(value: Int)

    /**
     * Delete the currently stored preference.
     */
    fun delete()

}
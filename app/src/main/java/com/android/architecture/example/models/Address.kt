package com.android.architecture.example.models


data class Address(
        val street: String? = null,
        val suite: String? = null,
        val city: String? = null,
        val zipCode: String? = null,
        val geo: Location? = null
)
package com.android.architecture.example.network.apiresponses


data class AddressEnvelope(
        val street: String?,
        val suite: String?,
        val city: String?,
        val zipcode: String?,
        val geo: LocationEnvelope?
)
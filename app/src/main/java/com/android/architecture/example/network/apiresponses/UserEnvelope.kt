package com.android.architecture.example.network.apiresponses


data class UserEnvelope(
        val id: Int?,
        val name: String?,
        val username: String?,
        val email: String?,
        val address: AddressEnvelope?,
        val phone: String?,
        val website: String?,
        val company: CompanyEnvelope?
)
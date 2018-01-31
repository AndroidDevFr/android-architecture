package com.android.architecture.example.network


enum class ApiEndpoint(
        private val apiName: String,
        private val url: String
) {

    SAMPLE("jsonplaceholder", "https://jsonplaceholder.typicode.com/");

    fun url(): String {
        return url
    }

    override fun toString(): String {
        return apiName
    }

}
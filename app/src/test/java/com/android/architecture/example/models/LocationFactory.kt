package com.android.architecture.example.models


object LocationFactory {

    @JvmStatic
    fun creator(): Location {
        return Location(
                lat = 0.0,
                lng = 90.0
        )
    }

}
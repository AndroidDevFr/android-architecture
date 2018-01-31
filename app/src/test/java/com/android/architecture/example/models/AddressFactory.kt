package com.android.architecture.example.models


object AddressFactory {

    @JvmStatic
    fun creator(): Address {
        return Address(
                street = "street",
                city = "city",
                geo = LocationFactory.creator(),
                suite = "suite",
                zipCode = "zip code"
        )
    }

}
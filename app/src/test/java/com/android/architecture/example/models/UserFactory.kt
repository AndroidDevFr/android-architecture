package com.android.architecture.example.models


object UserFactory {

    @JvmStatic
    fun creator(): User {
        return User(
                id = 1234567890,
                name = "name",
                userName = "user name",
                email = "android@android.com",
                address = AddressFactory.creator(),
                phone = "+33123456789",
                website = "www.android.com",
                company = CompanyFactory.creator()
        )
    }

}
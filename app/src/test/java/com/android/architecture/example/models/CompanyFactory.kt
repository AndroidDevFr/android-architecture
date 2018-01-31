package com.android.architecture.example.models


object CompanyFactory {

    @JvmStatic
    fun creator(): Company {
        return Company(
                name = "name",
                bs = "bs"
        )
    }

}
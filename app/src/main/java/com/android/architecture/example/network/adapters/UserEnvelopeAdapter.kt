package com.android.architecture.example.network.adapters

import com.android.architecture.example.models.Address
import com.android.architecture.example.models.Company
import com.android.architecture.example.models.Location
import com.android.architecture.example.models.User
import com.android.architecture.example.network.apiresponses.UserEnvelope


object UserEnvelopeAdapter {

    @JvmStatic
    fun fromJson(envelopes: List<UserEnvelope>): List<User> {

        val users = arrayListOf<User>()

        envelopes.forEach { envelope ->
            val location = Location(
                    envelope.address?.geo?.lat?.toDouble(),
                    envelope.address?.geo?.lng?.toDouble()
            )

            val address = Address(
                    envelope.address?.street,
                    envelope.address?.suite,
                    envelope.address?.city,
                    envelope.address?.zipcode,
                    location
            )

            val company = Company(
                    envelope.company?.name,
                    envelope.company?.bs
            )

            users.add(
                    User(
                            envelope.id,
                            envelope.name,
                            envelope.username,
                            envelope.email,
                            address,
                            envelope.phone,
                            envelope.website,
                            company
                    )
            )
        }

        return users
    }

    @JvmStatic
    fun fromJson(envelope: UserEnvelope): User {

        val location = Location(
                envelope.address?.geo?.lat?.toDouble(),
                envelope.address?.geo?.lng?.toDouble()
        )

        val address = Address(
                envelope.address?.street,
                envelope.address?.suite,
                envelope.address?.city,
                envelope.address?.zipcode,
                location
        )

        val company = Company(
                envelope.company?.name,
                envelope.company?.bs
        )

        return User(
                envelope.id,
                envelope.name,
                envelope.username,
                envelope.email,
                address,
                envelope.phone,
                envelope.website,
                company
        )
    }

}
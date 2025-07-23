package com.ticketera.data

import com.ticketera.model.User
import com.ticketera.model.UserRole
import org.springframework.http.HttpHeaders

class UserData {
    companion object {

        val headersMap = mapOf(
            "x-Roles" to "USER,X-USER-ROLE,ADMIN",
            "X-name" to "any-name",
            "x-email" to "any@mail.com",
        )

        val httpHeaders = HttpHeaders().apply {
            add("X-Roles", "ADMIN,USER")
            add("Authorization", "Bearer token")
        }

        val user = User(
            "any-name",
            "family-name",
            "any@mail.com",
            setOf(UserRole.USER)
        )
    }
}
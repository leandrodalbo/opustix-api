package com.ticketera.service

import com.ticketera.model.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AuthHeadersServiceTest {

    private val authHeadersService = AuthHeadersService()

    private val headers = mapOf(
        "x-Roles" to "USER,X-USER-ROLE,ADMIN",
        "X-name" to "any-name",
        "x-email" to "any@mail.com",
    )

    @Test
    fun shouldGetTheUserFromHeaders() {

        val user = authHeadersService.getUser(headers)

        assertThat(user.name).isEqualTo(headers["X-name"])
        assertThat(user.email).isEqualTo(headers["x-email"])
        assertThat(user.roles).isEqualTo(setOf(UserRole.ADMIN, UserRole.USER))
    }


    @Test
    fun shouldValidateAdminOrOrganizer() {
        assertThat(authHeadersService.isAdminOrOrganizer(headers)).isTrue
    }
}
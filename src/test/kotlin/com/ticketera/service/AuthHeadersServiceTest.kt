package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.model.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AuthHeadersServiceTest : TestData() {

    private val authHeadersService = AuthHeadersService()

    @Test
    fun shouldGetTheUserFromHeaders() {

        val user = authHeadersService.getUser(headersMap)

        assertThat(user.name).isEqualTo(headersMap["X-name"])
        assertThat(user.email).isEqualTo(headersMap["x-email"])
        assertThat(user.roles).isEqualTo(setOf(UserRole.ADMIN, UserRole.USER))
    }


    @Test
    fun shouldValidateAdminOrOrganizer() {
        assertThat(authHeadersService.isAdminOrOrganizer(headersMap)).isTrue
    }

    @Test
    fun shouldValidateAUser() {
        assertThat(authHeadersService.isAUser(headersMap)).isTrue
    }
}
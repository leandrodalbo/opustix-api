package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.model.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AuthHeadersServiceTest {

    private val authHeadersService = AuthHeadersService()

    @Test
    fun shouldGetTheUserFromHeaders() {

        val user = authHeadersService.getUser(TestData.headersMap)

        assertThat(user.name).isEqualTo(TestData.headersMap["X-name"])
        assertThat(user.email).isEqualTo(TestData.headersMap["x-email"])
        assertThat(user.roles).isEqualTo(setOf(UserRole.ADMIN, UserRole.USER))
    }


    @Test
    fun shouldValidateAdminOrOrganizer() {
        assertThat(authHeadersService.isAdminOrOrganizer(TestData.headersMap)).isTrue
    }

    @Test
    fun shouldValidateAUser() {
        assertThat(authHeadersService.isAUser(TestData.headersMap)).isTrue
    }
}
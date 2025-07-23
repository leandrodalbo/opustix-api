package com.ticketera.service

import com.ticketera.data.UserData
import com.ticketera.model.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AuthHeadersServiceTest {

    private val authHeadersService = AuthHeadersService()

    @Test
    fun shouldGetTheUserFromHeaders() {

        val user = authHeadersService.getUser(UserData.headersMap)

        assertThat(user.name).isEqualTo(UserData.headersMap["X-name"])
        assertThat(user.email).isEqualTo(UserData.headersMap["x-email"])
        assertThat(user.roles).isEqualTo(setOf(UserRole.ADMIN, UserRole.USER))
    }


    @Test
    fun shouldValidateAdminOrOrganizer() {
        assertThat(authHeadersService.isAdminOrOrganizer(UserData.headersMap)).isTrue
    }

    @Test
    fun shouldValidateAUser() {
        assertThat(authHeadersService.isAUser(UserData.headersMap)).isTrue
    }
}
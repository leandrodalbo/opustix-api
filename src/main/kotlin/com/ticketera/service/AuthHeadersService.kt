package com.ticketera.service

import com.ticketera.model.User
import com.ticketera.model.UserRole
import org.springframework.stereotype.Service

@Service
class AuthHeadersService {

    fun getUser(headers: Map<String, String>) =
        User(
            name = headers.getCaseInsensitive("X-Name") ?: "",
            familyName = headers.getCaseInsensitive("X-FamilyName") ?: "",
            email = headers.getCaseInsensitive("X-Email") ?: "",
            roles = extractRoles(headers.getCaseInsensitive("X-Roles"))
        )

    fun isAdminOrOrganizer(headers: Map<String, String>) =
        getUser(headers).let {
            it.roles.contains(UserRole.ADMIN) || it.roles.contains(UserRole.ORGANIZER)
        }

    private fun extractRoles(roles: String?) = roles
        ?.split(",")
        ?.mapNotNull { role ->
            runCatching {
                UserRole.valueOf(role.trim())
            }.getOrNull()
        }
        ?.toSet()
        ?: emptySet()

    private fun Map<String, String>.getCaseInsensitive(key: String): String? =
        this.entries.firstOrNull { it.key.equals(key, ignoreCase = true) }?.value
}
package com.ticketera.model

data class User(
    val name: String,
    val familyName: String,
    val email: String,
    val roles: Set<UserRole>
)

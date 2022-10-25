package com.artemissoftware.domain.models.profile

data class User(val email: String?, val name: String?, var favorites: List<String> = emptyList())

package com.locathor.brzodolokacije.data.mappers

import com.locathor.brzodolokacije.data.local.user.UserEntity
import com.locathor.brzodolokacije.domain.model.User

fun UserEntity.toUser(): User
    = User(
        username = username,
        email = email,
        profilePicUrl = profilePicUrl,
        name = name,
        surname = surname
    )
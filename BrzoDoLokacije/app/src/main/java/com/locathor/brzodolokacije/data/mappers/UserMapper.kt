package com.locathor.brzodolokacije.data.mappers

import com.locathor.brzodolokacije.data.local.user.UserEntity
import com.locathor.brzodolokacije.data.remote.dto.UserDto
import com.locathor.brzodolokacije.domain.model.User

fun UserEntity.toUser(): User
    = User(
        username = username,
        email = email,
        profilePicUrl = profilePicUrl,
        name = name,
        surname = surname
    )

fun UserDto.toUser(): User
 = User(
        username = username,
        email = email,
        profilePicUrl = profilePicUrl,
        name = name,
        surname = surname
)

fun UserDto.toUserEntity(): UserEntity =
UserEntity(
        username = username,
        email = email,
        profilePicUrl = profilePicUrl,
        name = name,
        surname = surname
)
package com.locathor.brzodolokacije.data.mappers

import com.locathor.brzodolokacije.data.local.user.UserEntity
import com.locathor.brzodolokacije.data.remote.dto.UserDto
import com.locathor.brzodolokacije.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        name = name,
        surname = surname,
        username = username
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        name = name,
        username = username,
        surname = surname
    )
}

fun UserDto.toUser(): User {
    return User(
        name = name ?: "",
        surname = surname ?: "",
        username = username ?: ""
    )
}
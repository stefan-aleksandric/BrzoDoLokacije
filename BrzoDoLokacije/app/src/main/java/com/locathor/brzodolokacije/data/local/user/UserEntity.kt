package com.locathor.brzodolokacije.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val username: String,
    val name: String,
    val surname: String,
    val jwt: String? = null,
    @PrimaryKey val id: Int? = null
)
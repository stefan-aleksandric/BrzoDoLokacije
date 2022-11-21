package com.locathor.brzodolokacije.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class UserEntity(
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val profilePicUrl: String,
    @PrimaryKey val userId: Int? = null
)
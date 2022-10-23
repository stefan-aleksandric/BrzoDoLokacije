package com.locathor.brzodolokacije.domain.repository

import com.locathor.brzodolokacije.domain.model.User
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(
        username: String,
        email: String,
        surname: String,
        name: String,
        //profilePic: String,
        password: String
    ): Flow<Resource<User>>
}
package com.locathor.brzodolokacije.domain.repository

import com.locathor.brzodolokacije.domain.model.User
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow

interface BrzoDoLokacijeRepository {
    suspend fun getUsers(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<User>>>
}
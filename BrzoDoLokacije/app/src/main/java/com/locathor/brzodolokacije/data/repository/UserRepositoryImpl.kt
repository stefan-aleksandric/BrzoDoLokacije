package com.locathor.brzodolokacije.data.repository

import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.domain.model.User
import com.locathor.brzodolokacije.domain.repository.UserRepository
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
): UserRepository {
    override suspend fun registerUser(
        username: String,
        email: String,
        surname: String,
        name: String,
        password: String
    ): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val responseUser = try {
                api.registerUser(
                    username = username,
                    email = email,
                    surname = surname,
                    name = name,
                    password = password
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            }
            responseUser?.let {
                emit(Resource.Success(data = User(username, name, surname, email)))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }

}
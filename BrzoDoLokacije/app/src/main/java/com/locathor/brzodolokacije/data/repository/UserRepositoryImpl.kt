package com.locathor.brzodolokacije.data.repository

import android.util.Log
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.data.remote.dto.AuthResult
import com.locathor.brzodolokacije.data.remote.dto.LoginRequest
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
    private val api: UserApi,
    private val sessionManager: SessionManager
): UserRepository {
    override suspend fun registerUser(
        username: String,
        email: String,
        name: String,
        surname: String,
        //profilePic: String,
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
                Log.d("DEBUG", it.toString());
                emit(Resource.Loading(isLoading = false))
            }
        }
    }

    override suspend fun loginUser(username: String, password: String): Flow<Resource<AuthResult<Unit>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val loginResponse = try {
                api.loginUser(
                    LoginRequest(username, password)
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            } catch (e: HttpException) {
                if(e.code() == 401)
                    emit(Resource.Success(data = AuthResult.Unauthorized()))
                else
                    emit(Resource.Success(data = AuthResult.UnknownError()))
                null
            }
            loginResponse?.let {
                sessionManager.refreshToken(loginResponse.authToken) /// do refresh logic
                Log.d("AUTH_TOKEN:",sessionManager.getAccessToken().toString())
                emit(Resource.Success(data = AuthResult.Authorized()))
                Log.d("loginResponse", it.toString())
                emit(Resource.Loading(isLoading = false))
            }
        }
    }


    //authenticate
    /*
    *
    *
    *
    *
    *
    * */

}
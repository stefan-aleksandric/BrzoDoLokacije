package com.locathor.brzodolokacije.data.repository

import android.util.Log
import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.mappers.toUser
import com.locathor.brzodolokacije.data.mappers.toUserEntity
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.data.remote.dto.AuthenticationResponse
import com.locathor.brzodolokacije.util.AuthResult
import com.locathor.brzodolokacije.data.remote.dto.LoginRequest
import com.locathor.brzodolokacije.data.remote.dto.RegisterRequest
import com.locathor.brzodolokacije.data.services.SessionManager
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
    private val db: BrzoDoLokacijeDatabase,
    private val sessionManager: SessionManager
): UserRepository {
    private val dao = db.userDao


    override suspend fun registerUser(
        username: String,
        email: String,
        name: String,
        surname: String,
//        profilePic: String,
        password: String
    ): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val authResponse = try {
                api.registerUser(
                    RegisterRequest(
                        username = username,
                        email = email,
                        surname = surname,
                        name = name,
                        password = password,
                        profilePic = ""
                    )
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
            authResponse?.let {
                authenticateUser(it)
                dao.insertUser(listOf(it.user.toUserEntity()))
                emit(Resource.Success(data = it.user.toUser()))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun loginUser(username: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val authResponse = try {
                api.loginUser(
                    LoginRequest(username, password)
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            } catch (e: HttpException) {
                if(e.code() == 401)
                    emit(Resource.Error(data=null, message = "Unauthenticated"))
                else
                    emit(Resource.Error(data=null, message = e.message()))
                null
            }
            authResponse?.let {
                authenticateUser(it)
                dao.insertUser(listOf(it.user.toUserEntity()))
                emit(Resource.Success(data = it.user.toUser()))
                Log.d("LOGGED_IN", sessionManager.getAccessToken()+" "+sessionManager.getCurrentUsername())
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    private fun authenticateUser(it: AuthenticationResponse) {
        sessionManager.refreshToken(it.authToken.value) /// do refresh logic
        sessionManager.setCurrentUsername(it.user.username)
    }

    override suspend fun getUser(id: Int): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                val username = sessionManager.getCurrentUsername()
                dao.getUserForUsername(username!!).first()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            user?.let{
                emit(Resource.Success(data = user.toUser()))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}
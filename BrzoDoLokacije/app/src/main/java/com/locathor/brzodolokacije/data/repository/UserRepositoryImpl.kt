package com.locathor.brzodolokacije.data.repository

import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.mappers.toUser
import com.locathor.brzodolokacije.data.mappers.toUserEntity
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
    private val api: UserApi,
    private val db: BrzoDoLokacijeDatabase
): UserRepository {
    private val dao = db.userDao

    override suspend fun getUsers(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading(true))
            val localUsers = dao.getAllUsers()
            emit(Resource.Success(
                data = localUsers.map { it.toUser() }
            ))

            val isDbEmpty = localUsers.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){ //we already returned an emit with Resource.Success<data from cache>
                emit(Resource.Loading(false)) //stop loading indication
                return@flow
            }
            val remoteUsers = try {
                api.getAllUsers()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load user data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load user data"))
                null
            }

            remoteUsers?.let { users ->
                dao.clearUsers()
                dao.insertUsers(
                    users.map { it.toUser().toUserEntity() }
                )
                emit(Resource.Success(
                    data = dao.getAllUsers()
                        .map { it.toUser() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

}
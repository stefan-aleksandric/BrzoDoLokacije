package com.locathor.brzodolokacije.data.repository

import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.mappers.toPost
import com.locathor.brzodolokacije.data.mappers.toPostEntity
import com.locathor.brzodolokacije.data.remote.PostApi
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val db: BrzoDoLokacijeDatabase
): PostRepository {
    private val dao = db.postDao

    override suspend fun getPosts(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Post>>> {
        return flow {
            emit(Resource.Loading(true))
            val localPosts = dao.getAllPosts()
            emit(Resource.Success(
                data = localPosts.map { it.toPost() }
            ))

            val isDbEmpty = localPosts.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){ //we already returned an emit with Resource.Success<data from cache>
                emit(Resource.Loading(false)) //stop loading indication
                return@flow
            }
            val remotePosts = try {
                api.getAllPosts()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load post data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load post data"))
                null
            }

            remotePosts?.let { posts ->
                dao.clearPosts()
                dao.insertPosts(
                    posts.map { it.toPostEntity() }
                )
                emit(Resource.Success(
                    data = dao.getAllPosts()
                        .map { it.toPost() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

}
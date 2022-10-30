package com.locathor.brzodolokacije.data.repository

import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.mappers.toPost
import com.locathor.brzodolokacije.data.mappers.toPostEntity
import com.locathor.brzodolokacije.data.mappers.toRequest
import com.locathor.brzodolokacije.data.remote.PostApi
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import com.locathor.brzodolokacije.domain.model.Post as Post

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

    override suspend fun getPost(id: Int, fetchFromRemote: Boolean): Flow<Resource<List<Post>>> {
        return flow {
            emit(Resource.Loading(true))
            val localPost = dao.getPostForId(id)
            emit(Resource.Success(
                data = localPost.map { it.toPost() }
            ))

            val isDbEmpty = localPost.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){ //we already returned an emit with Resource.Success<data from cache>
                emit(Resource.Loading(false)) //stop loading indication
                return@flow
            }
            val remotePost = try {
                api.getPostsForId(id)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load post data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load post data"))
                null
            }

            remotePost?.let { post ->
                dao.insertPosts(
                    listOf<PostEntity>(post.toPostEntity())
                )
                emit(Resource.Success(
                    data = dao.getPostForId(id)
                        .map { it.toPost() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun createPost(post: Post): Flow<Resource<Post>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val response = try {
                api.createPost(post.toRequest())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't create post data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't create post data"))
                null
            }

            response?.let {
                dao.insertPosts(
                    listOf(
                        it.toPostEntity()
                    )
                )
                emit(Resource.Success(
                    data=dao.getPostForId(response.postId)
                        .first()
                        .toPost()
                ))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }


}



package com.locathor.brzodolokacije.data.repository

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.mappers.toPost
import com.locathor.brzodolokacije.data.mappers.toPostEntity
import com.locathor.brzodolokacije.data.remote.PostApi
import com.locathor.brzodolokacije.data.remote.dto.CreatePostRequest
import com.locathor.brzodolokacije.data.services.SessionManager
import com.locathor.brzodolokacije.domain.model.CreatePost
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.util.Constants.IMAGES
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import com.locathor.brzodolokacije.domain.model.Post as Post

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val db: BrzoDoLokacijeDatabase,
    private val storage: FirebaseStorage,
    private val sessionManager: SessionManager
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
            }
            emit(Resource.Loading(false))
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
                api.getPostForId(id)
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
                    listOf(post.toPostEntity())
                )
                emit(Resource.Success(
                    data = dao.getPostForId(id)
                        .map { it.toPost() }
                ))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun createPost(post: CreatePost): Flow<Resource<Post>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val downloadUrls = try {
                post.mediaUris.map {
                     storage.reference.child(IMAGES).child(UUID.randomUUID().toString())
                        .putFile(it).await()
                        .storage.downloadUrl.await()
                }
            } catch (e: Exception){
                emit(Resource.Error(e.message.toString()))
                e.printStackTrace()
                null
            }

            val response = try {
                api.createPost(CreatePostRequest(
                    title = post.title,
                    description = post.desc,
                    latitude = post.latitude,
                    longitude = post.longitude,
                    images = if( downloadUrls!!.isNotEmpty() )
                    {
                            Log.d("not empty", "")
                        downloadUrls.first().toString()
                    }
                            else "",
                    //                    images = downloadUrls!!.first().map{
//                         it.toString()
//                    },
                    createdDate = "2022-12-07T00:00:00",
                    ownerUsername = sessionManager.getCurrentUsername().toString()
                ))
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
//                dao.insertPosts(
//                    listOf(
//                        it.toPostEntity()
//                    )
//                )
                emit(Resource.Success(
                    data=Post(
                        title = post.title,
                        desc = post.desc,
                        latitude = post.latitude,
                        longitude = post.longitude,
                        createdAt = "",
                        mediaUris = listOf(downloadUrls!!.first().toString()),
                        ownerUsername = sessionManager.getCurrentUsername().toString(),
                        likeCount = 0,
                        commentCount = 0
                    )
                ))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }


}



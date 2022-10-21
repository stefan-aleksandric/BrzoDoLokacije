package com.locathor.brzodolokacije.data.local.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.locathor.brzodolokacije.data.local.user.UserEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM postentity")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("""
       SELECT * FROM postentity
       WHERE id = :id
    """)
    suspend fun getPostForId(id: Int): PostEntity

    @Query("DELETE FROM postentity")
    suspend fun clearPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(postEntities: List<PostEntity>)


}
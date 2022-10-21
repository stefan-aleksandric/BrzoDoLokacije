package com.locathor.brzodolokacije.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.locathor.brzodolokacije.data.local.post.PostDao
import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.local.user.UserDao
import com.locathor.brzodolokacije.data.local.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BrzoDoLokacijeDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val postDao: PostDao
}
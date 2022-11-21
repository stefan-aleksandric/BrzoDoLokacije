package com.locathor.brzodolokacije.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.locathor.brzodolokacije.data.local.post.PostDao
import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.local.user.UserDao
import com.locathor.brzodolokacije.data.local.user.UserEntity

@Database(
    entities = [
        PostEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(RoomConverters::class)
abstract class BrzoDoLokacijeDatabase: RoomDatabase() {
    abstract val postDao: PostDao
    abstract val userDao: UserDao
}
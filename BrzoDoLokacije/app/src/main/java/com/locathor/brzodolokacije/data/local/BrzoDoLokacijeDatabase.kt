package com.locathor.brzodolokacije.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.locathor.brzodolokacije.data.local.post.PostDao
import com.locathor.brzodolokacije.data.local.post.PostEntity

@Database(
    entities = [
        PostEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BrzoDoLokacijeDatabase: RoomDatabase() {
    abstract val postDao: PostDao
}
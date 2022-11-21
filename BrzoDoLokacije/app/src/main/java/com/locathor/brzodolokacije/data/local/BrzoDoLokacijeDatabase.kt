package com.locathor.brzodolokacije.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.locathor.brzodolokacije.data.local.post.PostDao
import com.locathor.brzodolokacije.data.local.post.PostEntity

@Database(
    entities = [
        PostEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class BrzoDoLokacijeDatabase: RoomDatabase() {
    abstract val postDao: PostDao
}
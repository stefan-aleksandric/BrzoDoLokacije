package com.locathor.brzodolokacije.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(
        userEntities: List<UserEntity>
    )

    @Query("""
            SELECT *
            FROM userentity
            WHERE username LIKE '%' || :query || '%'
            """)
    suspend fun getUsersForUsername(query: String): List<UserEntity>

    @Query("SELECT * FROM userentity")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun clearUsers()
}
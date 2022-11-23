package com.locathor.brzodolokacije.data.local.user


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.locathor.brzodolokacije.domain.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    suspend fun getUsers(): List<UserEntity>

    @Query("""SELECT * FROM userentity
                WHERE userId = :id""")
    suspend fun getUserForId(id: Int): List<UserEntity>

    @Query("""SELECT * FROM userentity
        WHERE username = :username""")
    suspend fun getUserForUsername(username: String): List<UserEntity>
    
    @Query("DELETE FROM userentity")
    suspend fun clearUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<UserEntity>)
}
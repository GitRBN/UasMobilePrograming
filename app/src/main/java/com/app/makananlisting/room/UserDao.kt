package com.app.makananlisting.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.makananlisting.model.User
import org.jetbrains.annotations.Nullable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User): Long

    @Query("SELECT * FROM " + MakananDB.USER_TABLE + " WHERE username = :username AND password = :password")
    fun login(username: String, password: String): User?
}
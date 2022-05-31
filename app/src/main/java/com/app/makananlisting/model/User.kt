package com.app.makananlisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.makananlisting.room.MakananDB

@Entity(tableName = MakananDB.USER_TABLE)
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val password: String
)

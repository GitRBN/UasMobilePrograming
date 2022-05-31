package com.app.makananlisting.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.makananlisting.model.Makanan
import com.app.makananlisting.model.User

@Database(version = 1, entities = [User::class, Makanan::class], exportSchema = false)
abstract class MakananDB: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "makananlisting"
        const val USER_TABLE = "user_table"
        const val MAKANAN_TABLE = "makanan_table"
    }

    //data access object untuk crud table di database sqlite
    abstract fun makananDao(): MakananDao
    abstract fun userDao(): UserDao
}
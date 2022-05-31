package com.app.makananlisting.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.makananlisting.model.Makanan

@Dao
interface MakananDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(makanan: Makanan): Long

    @Query("SELECT * FROM " + MakananDB.MAKANAN_TABLE + " ORDER BY harga DESC")
    fun getAll(): List<Makanan>
}
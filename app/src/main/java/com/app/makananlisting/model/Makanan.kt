package com.app.makananlisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.makananlisting.room.MakananDB

@Entity(tableName = MakananDB.MAKANAN_TABLE)
data class Makanan(
    @PrimaryKey
    val id: Int,
    val nama: String,
    val gambar: Int,
    val harga: Int
)

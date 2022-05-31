package com.app.makananlisting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.makananlisting.databinding.ActivityMainBinding
import com.app.makananlisting.room.MakananDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db: MakananDB

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)

        db = Room.databaseBuilder(this, MakananDB::class.java, MakananDB.DATABASE_NAME)
            .allowMainThreadQueries().build()

        binding.mainLogout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val adapter = MainAdapter(db.makananDao().getAll(), this)
        binding.mainRecycler.adapter = adapter
        binding.mainRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
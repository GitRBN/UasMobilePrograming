package com.app.makananlisting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.app.makananlisting.databinding.ActivityLoginBinding
import com.app.makananlisting.model.Makanan
import com.app.makananlisting.model.User
import com.app.makananlisting.room.MakananDB

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var db: MakananDB

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        if(sharedPreferences.contains("username")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        db = Room.databaseBuilder(this, MakananDB::class.java, MakananDB.DATABASE_NAME)
            .allowMainThreadQueries().build()

        initDatabase()

        supportActionBar?.title = getString(R.string.login)

        binding.loginAction.setOnClickListener {
            val username = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()) {
                val user = db.userDao().login(username, password)
                if(user != null) {
                    sharedPreferences.edit()
                        .putString("name", user.name)
                        .putString("username", user.username)
                        .putString("password", user.password)
                        .apply()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "User tidak terdaftar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username atau password kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    private fun initDatabase() {
        db.userDao().insert(User(1, "John Doe", "johndoe", "john123"))
        db.userDao().insert(User(2, "Jane Doe", "janedoe", "jane123"))
        db.userDao().insert(User(3, "Hello World", "helloworld", "hello123"))
        db.makananDao().insert(Makanan(1, "Chicken Burger", R.drawable.makanan1, 15000))
        db.makananDao().insert(Makanan(2, "Cheesy Rasher Fries", R.drawable.makanan2, 12728))
        db.makananDao().insert(Makanan(3, "Cheesy Fries", R.drawable.makanan3, 11818))
        db.makananDao().insert(Makanan(4, "Beef Burger", R.drawable.makanan4, 19091))
        db.makananDao().insert(Makanan(5, "BBQ Rasher + Chese Burger", R.drawable.makanan5, 42727))
        db.makananDao().insert(Makanan(6, "BBQ Rasher + Mozza Chicker", R.drawable.makanan6, 42727))
        db.makananDao().insert(Makanan(7, "Cheese Burger + Whooper JR", R.drawable.makanan7, 42727))
        db.makananDao().insert(Makanan(8, "Bogo Italian Mozza XL + Mozza Tendercrisp", R.drawable.makanan8, 69545))
        db.makananDao().insert(Makanan(9, "Bogo Italian Mozza XL + QC Tendercrisp", R.drawable.makanan9, 69545))
        db.makananDao().insert(Makanan(10, "Bogo Whopper + Mozza Tendercrisp", R.drawable.makanan10, 69545))
    }
}
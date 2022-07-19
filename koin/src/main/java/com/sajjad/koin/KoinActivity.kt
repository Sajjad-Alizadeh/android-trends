package com.sajjad.koin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sajjad.koin.databinding.ActivityKoinBinding
import com.sajjad.koin.retrofit.RetrofitActivity
import com.sajjad.koin.room.RoomActivity

class KoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRoomActivity.setOnClickListener {
                Intent(this@KoinActivity, RoomActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
            btnRetrofitActivity.setOnClickListener {
                Intent(this@KoinActivity, RetrofitActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
        }
    }
}
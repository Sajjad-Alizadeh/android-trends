package com.sajjad.hilt.simple

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sajjad.hilt.databinding.ActivityHiltBinding
import com.sajjad.hilt.retrofit.RetrofitActivity
import com.sajjad.hilt.room.RoomActivity
import com.sajjad.hilt.simple.di.APP_NAME
import com.sajjad.hilt.simple.di.USERNAME
import com.sajjad.hilt.simple.di.qualifier.Family
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHiltBinding

    @Inject
    lateinit var name: String

    //Using qualifier
    @Inject
    @Family
    lateinit var family: String

    //Using named
    @Inject
    @Named(USERNAME)
    lateinit var username: String

    @Inject
    @Named(APP_NAME)
    lateinit var appName: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvName.text = "Name: $name"
            tvFamily.text = "Family: $family"
            tvUsername.text = "Username: $username"
            tvAppName.text = "App name: $appName"

            btnStartRoomActivity.setOnClickListener {
                startActivity(Intent(this@HiltActivity, RoomActivity::class.java))
            }

            btnStartRetrofitActivity.setOnClickListener {
                startActivity(Intent(this@HiltActivity, RetrofitActivity::class.java))
            }
        }

    }
}
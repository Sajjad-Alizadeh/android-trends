package com.sajjad.koin.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.koin.data.UserModel
import com.sajjad.koin.databinding.ActivityRoomBinding
import org.koin.android.ext.android.inject

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private val viewModel: RoomViewModel by inject()
    private val entity: UserModel by inject()
    private val adapter: UserAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@RoomActivity, LinearLayoutManager.VERTICAL, false)
            rvUser.adapter = adapter
            viewModel.getAllUser()

            btnSave.setOnClickListener {
                entity.fullName = "${etName.text}"
                entity.age = etAge.text.toString().toInt()
                viewModel.addUser(entity)
                viewModel.getAllUser()
            }

        }

        viewModel.userModelListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }
}
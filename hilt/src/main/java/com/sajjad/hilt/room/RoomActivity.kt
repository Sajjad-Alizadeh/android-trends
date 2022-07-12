package com.sajjad.hilt.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.hilt.databinding.ActivityRoomBinding
import com.sajjad.hilt.room.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var user: User

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter.submitList(repository.getUsers())

        binding.apply {
            //click
            btnAdd.setOnClickListener {
                user.name = etName.text.toString()
                repository.addUser(user)
                userAdapter.submitList(repository.getUsers())
            }

            //init rv
            rvUser.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(this@RoomActivity)
            }

            //adapter click
            userAdapter.onItemClickListener {
                Toast.makeText(this@RoomActivity, "${it.id} -> ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
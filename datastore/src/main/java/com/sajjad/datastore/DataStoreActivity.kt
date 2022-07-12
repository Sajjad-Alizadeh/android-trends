package com.sajjad.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.sajjad.datastore.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataStoreBinding
    private val dataStore: DataStore<Preferences> by preferencesDataStore("User_info")

    private val usernameKey = stringPreferencesKey("USERNAME")
    private val ageKey = intPreferencesKey("AGE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //click
            btnSave.setOnClickListener {
                CoroutineScope(IO).launch {
                    saveData(
                        etUsername.text.toString().trim(),
                        etAge.text.toString().toInt()
                    )
                }
            }

            //get data
            lifecycleScope.launchWhenCreated {
                getUsername().collect {
                    tvUsername.text = "Username is: $it"
                }
            }
            lifecycleScope.launchWhenCreated {
                getAge().collect {
                    tvAge.text = "Age is: $it"
                }
            }
        }

    }

    private suspend fun saveData(name: String, age: Int) {
        dataStore.edit {
            it[usernameKey] = name
            it[ageKey] = age
        }
    }

    private fun getUsername() = dataStore.data.map {
        it[usernameKey] ?: ""
    }

    private fun getAge() = dataStore.data.map {
        it[ageKey] ?: -1
    }
}
package com.sajjad.movie_project.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sajjad.movie_project.common.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_DATASTORE_NAME)
        val tokenKey = stringPreferencesKey(Constants.TOKEN)
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit {
            it[tokenKey] = token
        }
    }

    fun getUserToken() = context.dataStore.data.map {
        it[tokenKey] ?: ""
    }
}
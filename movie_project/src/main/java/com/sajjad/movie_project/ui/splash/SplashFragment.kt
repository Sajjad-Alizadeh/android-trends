package com.sajjad.movie_project.ui.splash

import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.sajjad.movie_project.R
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.data.dataStore.UserDataStore
import com.sajjad.movie_project.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var userDataStore: UserDataStore

    override fun initViews() {
        lifecycle.coroutineScope.launchWhenCreated {
            delay(2000)
            userDataStore.getUserToken().collect {
                if (it.isEmpty())
                    findNavController().navigate(R.id.actionSplashToRegister)
                else
                    findNavController().navigate(R.id.actionToHome)
            }
        }
    }

    override fun observe() {

    }

    override fun callApi() {

    }

}
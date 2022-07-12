package com.sajjad.movie_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sajjad.movie_project.R
import com.sajjad.movie_project.common.BaseActivity
import com.sajjad.movie_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var navController: NavController

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    override fun initViews() {
        binding.apply {
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment || destination.id == R.id.imageFragment || destination.id == R.id.detailFragment) {
                    bottomNav.visibility = View.GONE
                } else {
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun observe() {

    }
}
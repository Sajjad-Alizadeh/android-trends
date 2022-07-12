package com.sajjad.movie_project.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalArgumentException

abstract class BaseActivity<T : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> T) :
    AppCompatActivity() {
    private var _binding: T? = null

    val binding: T
        get() = _binding as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)

        if (_binding == null)
            throw IllegalArgumentException("Binding cannot be null")

        setContentView(binding.root)

        initViews()
    }

    abstract fun initViews()

    abstract fun observe()

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(binding.root, message, duration).show()
    }
}
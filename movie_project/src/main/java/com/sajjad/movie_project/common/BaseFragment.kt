package com.sajjad.movie_project.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.sajjad.movie_project.R
import com.sajjad.movie_project.utils.gone
import com.sajjad.movie_project.utils.visible
import java.lang.IllegalArgumentException

abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null

    val binding: T
        get() = _binding as T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        if (_binding == null)
            throw IllegalArgumentException("Binding cannot be null")

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        observe()
    }

    abstract fun initViews()

    abstract fun observe()

    abstract fun callApi()

    fun navigateUp() {
        findNavController().navigateUp()
    }

    fun showLoadingView(mustShow: Boolean) {
        binding.apply {
            var loadingView = root.findViewById<View>(R.id.cl_loading)
            if (loadingView == null) {
                (root as ViewGroup).also { viewGroup ->
                    loadingView = LayoutInflater.from(requireContext())
                        .inflate(R.layout.loading_layout, viewGroup, false)
                    viewGroup.addView(loadingView)
                }

            }
            if (mustShow)
                loadingView.visible()
            else
                loadingView.gone()
        }
    }

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(binding.root, message, duration).show()
    }

}
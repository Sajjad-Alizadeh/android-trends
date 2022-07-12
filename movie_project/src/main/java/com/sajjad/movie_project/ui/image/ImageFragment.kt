package com.sajjad.movie_project.ui.image


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate) {

    private val viewModel: ImageViewModel by viewModels()
    private val args: ImageFragmentArgs by navArgs()

    override fun initViews() {
        binding.apply {
            //Click
            ivBack.setOnClickListener {
                navigateUp()
            }

            //Load image
            ivImage.load(args.url)
        }
    }

    override fun observe() {

    }

    override fun callApi() {

    }


}
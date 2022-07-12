package com.sajjad.movie_project.ui.register

import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.sajjad.movie_project.R
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.data.dataStore.UserDataStore
import com.sajjad.movie_project.data.request.RegisterUserRequest
import com.sajjad.movie_project.databinding.FragmentRegisterBinding
import com.sajjad.movie_project.utils.gone
import com.sajjad.movie_project.utils.showVisibility
import com.sajjad.movie_project.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var registerUserRequest: RegisterUserRequest

    @Inject
    lateinit var userDataStore: UserDataStore

    override fun initViews() {
        binding.apply {
            btnSubmit.setOnClickListener {
                //Validate input
                if (etName.text.toString().isEmpty() ||
                    etEmail.text.toString().isEmpty() ||
                    etPassword.text.toString().isEmpty()
                ) {
                    showSnackBar(getString(R.string.fillAllFields))
                } else {
                    registerUserRequest.name = etName.text.toString()
                    registerUserRequest.email = etEmail.text.toString()
                    registerUserRequest.password = etPassword.text.toString()
                    viewModel.registerUser(registerUserRequest)
                }
            }
        }
    }

    override fun observe() {
        viewModel.apply {

            registerUserLiveData.observe(viewLifecycleOwner) {
                lifecycle.coroutineScope.launchWhenCreated {
                    userDataStore.saveUserToken(it.id.toString())
                }
            }

            loadingLiveData.observe(viewLifecycleOwner) {
                binding.pbLoading.showVisibility(it)
                if (it)
                    binding.btnSubmit.gone()
                else
                    binding.btnSubmit.visible()

            }

        }
    }

    override fun callApi() {

    }
}
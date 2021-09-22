package com.ufscar.queimadas_front.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.databinding.FragmentLoginBinding
import com.ufscar.queimadas_front.utils.enable
import com.ufscar.queimadas_front.utils.handleApiError
import com.ufscar.queimadas_front.utils.toast
import com.ufscar.queimadas_front.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.progressBar.visible(false)
        binding.loginBtn.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.token)
                        viewModel.saveUserId(it.value.id)
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_mapsGraph)
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                    binding.loginBtn.enable(false)
                }
                is Resource.Failure -> handleApiError(it){ login() }
                else -> toast("Erro inesperado!")
            }
        })

        binding.passwordInput.addTextChangedListener {
            val email = binding.emailInput.text.toString().trim()
            binding.loginBtn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.emailInput.addTextChangedListener {
            val password = binding.passwordInput.text.toString().trim()
            binding.loginBtn.enable(password.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        viewModel.login(email, password)
    }

}
package com.ufscar.queimadas_front.ui.auth.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.databinding.FragmentRegistrationBinding
import com.ufscar.queimadas_front.ui.home.TestMapsActivity
import com.ufscar.queimadas_front.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrationBinding.bind(view)
        binding.progressBar.visible(false)
        binding.signUpButton.enable(false)
        viewModel.registrationResponse.observe(viewLifecycleOwner, {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        requireActivity().startNewActivity(TestMapsActivity::class.java)
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                    binding.signUpButton.enable(false)
                }
                is Resource.Failure -> handleApiError(it){ register() }
                else -> toast("Erro inesperado!")
            }
        })

        binding.passwordInput.addTextChangedListener {
            val email = binding.emailInput.text.toString().trim()
            binding.signUpButton.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.emailInput.addTextChangedListener {
            val password = binding.passwordInput.text.toString().trim()
            binding.signUpButton.enable(password.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.signUpButton.setOnClickListener {
            register()
        }

    }

    private fun register(){
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        viewModel.signup(email, password)
    }
}
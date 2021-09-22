package com.ufscar.queimadas_front.ui.auth.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.databinding.FragmentRegistrationBinding
import com.ufscar.queimadas_front.utils.enable
import com.ufscar.queimadas_front.utils.handleApiError
import com.ufscar.queimadas_front.utils.toast
import com.ufscar.queimadas_front.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    @Inject
    lateinit var userPreferences: UserPreferences

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
                        userPreferences.saveUserId(it.value.message)
                    }
                    findNavController().navigate(R.id.action_registrationFragment_to_mapsGraph)
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
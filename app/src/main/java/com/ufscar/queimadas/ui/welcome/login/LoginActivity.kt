package com.ufscar.queimadas.ui.welcome.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.ufscar.queimadas.R
import com.ufscar.queimadas.databinding.ActivityLoginBinding
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.ui.Listener
import com.ufscar.queimadas.ui.mapshome.MapsHomeActivity
import com.ufscar.queimadas.utils.hide
import com.ufscar.queimadas.utils.show
import com.ufscar.queimadas.utils.toast


class LoginActivity : AppCompatActivity(),
    Listener<UserResponse> {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        viewModel.authListener = this
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(genericResponse: LiveData<UserResponse>) {
        genericResponse.observe(this, {
            binding.progressBar.hide()
            toast(it.userId.toString())
        })
        val intent = Intent(this, MapsHomeActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        binding.progressBar.hide()
        toast(message)
    }


}

package com.ufscar.queimadas.ui.welcome.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.ufscar.queimadas.R
import com.ufscar.queimadas.databinding.ActivityRegistrationBinding
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.sharedPrefs.SharedPrefsManager
import com.ufscar.queimadas.ui.Listener
import com.ufscar.queimadas.ui.mapshome.MapsHomeActivity
import com.ufscar.queimadas.utils.hide
import com.ufscar.queimadas.utils.show
import com.ufscar.queimadas.utils.toast

class RegistrationActivity : AppCompatActivity(),
    Listener<UserResponse> {
    private val prefsManager: SharedPrefsManager = SharedPrefsManager.getInstance(this)
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(RegViewModel::class.java)
        binding.registrationViewModel = viewModel

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
            prefsManager.savePreference("id", it.userId.toString())
            prefsManager.savePreference("token", it.token)
        })
        val intent = Intent(this, MapsHomeActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        binding.progressBar.hide()
        toast(message)
    }
}
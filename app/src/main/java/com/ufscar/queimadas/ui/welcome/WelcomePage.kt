package com.ufscar.queimadas.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ufscar.queimadas.R
import com.ufscar.queimadas.databinding.ActivityWelcomePageBinding
import com.ufscar.queimadas.ui.welcome.login.LoginActivity
import com.ufscar.queimadas.ui.welcome.registration.RegistrationActivity

class WelcomePage : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}

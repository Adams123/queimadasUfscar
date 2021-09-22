package com.ufscar.queimadas_front.ui.auth.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.ufscar.queimadas_front.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Navigation.findNavController(this, R.id.nav_auth).handleDeepLink(intent)
        Navigation.findNavController(this, R.id.nav_maps).handleDeepLink(intent)
    }
}
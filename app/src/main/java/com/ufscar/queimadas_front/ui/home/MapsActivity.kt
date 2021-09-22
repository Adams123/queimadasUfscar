package com.ufscar.queimadas_front.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.ui.auth.welcome.WelcomeActivity
import com.ufscar.queimadas_front.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
   }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Navigation.findNavController(this, R.id.nav_auth).handleDeepLink(intent)
        Navigation.findNavController(this, R.id.nav_maps).handleDeepLink(intent)
    }

    fun performLogout() = lifecycleScope.launch {
        userPreferences.clear()
        startNewActivity(WelcomeActivity::class.java)
    }

}
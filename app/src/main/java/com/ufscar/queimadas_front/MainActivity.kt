package com.ufscar.queimadas_front

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.ui.auth.welcome.WelcomeActivity
import com.ufscar.queimadas_front.ui.home.TestMapsActivity
import com.ufscar.queimadas_front.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, {
            val activity = if(it == null) WelcomeActivity::class.java else TestMapsActivity::class.java
            startNewActivity(activity)
        })
    }
}
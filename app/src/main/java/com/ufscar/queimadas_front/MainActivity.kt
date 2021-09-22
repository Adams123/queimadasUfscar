package com.ufscar.queimadas_front

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.job.NotificationJobService
import com.ufscar.queimadas_front.ui.auth.welcome.WelcomeActivity
import com.ufscar.queimadas_front.ui.home.MapsActivity
import com.ufscar.queimadas_front.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, {
            val activity = if(it == null) WelcomeActivity::class.java else MapsActivity::class.java
            startNewActivity(activity)
        })
    }
}
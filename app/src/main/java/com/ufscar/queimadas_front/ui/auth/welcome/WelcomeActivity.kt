package com.ufscar.queimadas_front.ui.auth.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ufscar.queimadas_front.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}
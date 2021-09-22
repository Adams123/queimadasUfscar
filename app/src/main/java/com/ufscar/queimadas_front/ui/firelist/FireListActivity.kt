package com.ufscar.queimadas_front.ui.firelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ufscar.queimadas_front.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FireListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_fire_list)
    }
}
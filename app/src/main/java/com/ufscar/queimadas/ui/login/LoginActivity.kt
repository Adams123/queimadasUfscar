package com.ufscar.queimadas.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ufscar.queimadas.R
import com.ufscar.queimadas.databinding.ActivityLoginBinding
import com.ufscar.queimadas.sharedPrefs.SharedPrefsManager
import com.ufscar.queimadas.utils.toast


class LoginActivity : AppCompatActivity(), AuthListener {

    private val prefsManager: SharedPrefsManager = SharedPrefsManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingLogin: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        bindingLogin.loginViewModel = viewModel

        viewModel.authListener = this

        /*if(prefsManager.isFirstTimeLogin) {
            toast("Welcome to BUURN")
        }*/
    }

    fun test() {
        toast("Funfou")
    }

    override fun onStarted() {
        toast("Login started")
    }

    override fun onSuccess() {
        toast("Login success")
    }

    override fun onFailure(message: String) {
        toast("Login failure")
    }


}

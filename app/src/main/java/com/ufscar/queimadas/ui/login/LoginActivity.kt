package com.ufscar.queimadas.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ufscar.queimadas.R
import com.ufscar.queimadas.databinding.ActivityLoginBinding
import com.ufscar.queimadas.model.CreatedUserResponse
import com.ufscar.queimadas.sharedPrefs.SharedPrefsManager
import com.ufscar.queimadas.utils.hide
import com.ufscar.queimadas.utils.show
import com.ufscar.queimadas.utils.toast
import kotlinx.android.synthetic.main.activity_login.*


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

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(createdResponse: LiveData<CreatedUserResponse>) {
        createdResponse.observe(this, Observer {
            progressBar.hide()
            toast(it.userId.toString())
        })
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        toast(message)
    }


}

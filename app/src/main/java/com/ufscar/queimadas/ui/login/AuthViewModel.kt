package com.ufscar.queimadas.ui.login

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            authListener?.onFailure("Usuário e senha n pode ser vazio mano, pqp tu é burro?")
            return
        }

        authListener?.onSuccess()


    }

}
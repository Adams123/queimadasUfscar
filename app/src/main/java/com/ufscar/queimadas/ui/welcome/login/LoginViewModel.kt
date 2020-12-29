package com.ufscar.queimadas.ui.welcome.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.rest.repositories.UserRepository
import com.ufscar.queimadas.ui.Listener

class LoginViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: Listener<UserResponse>? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            authListener?.onFailure("Usuário e senha n pode ser vazio mano, pqp tu é burro?")
            return
        }

        val createdResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(createdResponse)
    }

}
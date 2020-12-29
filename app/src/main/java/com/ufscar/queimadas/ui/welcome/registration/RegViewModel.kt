package com.ufscar.queimadas.ui.welcome.registration

import android.view.View
import androidx.lifecycle.ViewModel
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.rest.repositories.UserRepository
import com.ufscar.queimadas.ui.Listener

class RegViewModel  : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: Listener<UserResponse>? = null

    fun onRegistrationButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            authListener?.onFailure("Usuário e senha n pode ser vazio mano, pqp tu é burro?")
            return
        }

        val loggedInResponse = UserRepository().userCreate(email!!, password!!)
        authListener?.onSuccess(loggedInResponse)
    }
}
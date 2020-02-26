package com.ufscar.queimadas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ufscar.queimadas.model.UserBearerToken
import com.ufscar.queimadas.service.LoginService
import mu.KotlinLogging

class MainActivity : AppCompatActivity() {

    private val logger = KotlinLogging.logger {}

    private val loginService: LoginService = LoginService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userNameField = findViewById<EditText>(R.id.emailInput)
        val passwordField = findViewById<EditText>(R.id.passwordInput)

        val btnSubmitLogin = findViewById<Button>(R.id.loginBtn)

        btnSubmitLogin.setOnClickListener {
            val username: String = userNameField.text.toString()
            val password: String = passwordField.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(
                    this@MainActivity,
                    "Usuário e senha n pode ser vazio mano",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var user: UserBearerToken = loginService.createUser(username, password)
                Toast.makeText(this@MainActivity, user.token.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }


}

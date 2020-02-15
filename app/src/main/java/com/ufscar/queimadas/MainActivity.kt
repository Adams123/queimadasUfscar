package com.ufscar.queimadas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userNameField = findViewById<EditText>(R.id.emailInput)
        var passwordField = findViewById<EditText>(R.id.passwordInput)

        var btnSubmitLogin = findViewById<Button>(R.id.loginBtn)

        btnSubmitLogin.setOnClickListener {
            var email = userNameField.text
            var password = passwordField.text

            if(email.isNullOrBlank() || password.isNullOrBlank()){
                Toast.makeText(this@MainActivity, "User and password cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this, MapsHome::class.java))
            }
        }
    }
}

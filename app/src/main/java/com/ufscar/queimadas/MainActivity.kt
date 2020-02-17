package com.ufscar.queimadas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.ufscar.queimadas.model.UserTest
import com.ufscar.queimadas.service.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //val loginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userNameField = findViewById<EditText>(R.id.emailInput)
        val passwordField = findViewById<EditText>(R.id.passwordInput)

        val btnSubmitLogin = findViewById<Button>(R.id.loginBtn)
/*
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.15.12:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        val testRetrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val loginService: LoginService = testRetrofit.create(LoginService::class.java)
        val testCall: Call<List<UserTest>> = loginService.testRest()

        btnSubmitLogin.setOnClickListener {
            val username: String = userNameField.text.toString()
            val password: String = passwordField.text.toString()

            if (username.isBlank() || password.isBlank()) {
//                Toast.makeText(this@MainActivity, "User and password cannot be empty", Toast.LENGTH_LONG).show()
                testCall.enqueue(object : Callback<List<UserTest>> {
                    override fun onFailure(call: Call<List<UserTest>>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            String.format("%s", t.message),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }

                    override fun onResponse(
                        call: Call<List<UserTest>>,
                        response: Response<List<UserTest>>
                    ) {
                        if (!response.isSuccessful) {
                            userNameField.setText("Failure")

                        } else {
                            val users: List<UserTest> = response.body()!!
                            userNameField.setText(users[0].body)

                        }
                        return
                    }
                })
            } else {
                val call = loginService
                /*call.enqueue(object: Callback<User>{
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        throw t
                    }
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val loggedUser: User? = response.body()
                        Toast.makeText(this@MainActivity, String.format("Logged in with %s on id %s", loggedUser?.username, loggedUser?.id.toString()), Toast.LENGTH_LONG).show()
                    }

                })*/
                startActivity(Intent(this, MapsHome::class.java))
            }
        }
    }
}

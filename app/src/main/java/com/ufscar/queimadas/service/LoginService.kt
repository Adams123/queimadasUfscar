package com.ufscar.queimadas.service

import android.content.Context
import android.widget.Toast
import com.ufscar.queimadas.model.User
import com.ufscar.queimadas.rest.RetrofitInitializer
import com.ufscar.queimadas.rest.endpoints.APILogin
import mu.KotlinLogging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginService(private val context: Context) {
    private val loginApi: APILogin = RetrofitInitializer().loginService()
    private val logger = KotlinLogging.logger {}

    fun Any.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun findUser(id: UUID): User? {
        val findCall: Call<User> = loginApi.findUser(id)
        var user: User? = null
        findCall.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.toast()
                logger.error { t.message }
                return
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                user = response.body()
                return
            }
        })
        return user
    }
}
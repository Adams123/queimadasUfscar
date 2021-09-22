package com.ufscar.queimadas_front.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.ui.auth.login.LoginFragment
import com.ufscar.queimadas_front.ui.home.MapsActivity
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

fun Fragment.logout() = lifecycleScope.launch {
    if (activity is MapsActivity) {
        (activity as MapsActivity).performLogout()
    }
}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    this.isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackbar("Verifique a conexão com a internet, por favor", retry)
        failure.errorCode == 401 -> {
            if(this is LoginFragment){
                requireView().snackbar("Email ou password errado")
            } else {
                logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}

fun AppCompatActivity.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    val view = this.window.decorView.rootView
    when {
        failure.isNetworkError -> view.snackbar("Verifique a conexão com a internet, por favor", retry)
        failure.errorCode == 401 -> {
            if(this is MapsActivity){
                view.snackbar("Erro ao tentar atualizar localizações")
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            view.snackbar(error)
        }
    }
}
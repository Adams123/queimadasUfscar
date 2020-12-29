package com.ufscar.queimadas.sharedPrefs

import android.content.Context
import com.ufscar.queimadas.model.User
import java.util.*

class SharedPrefsManager private constructor(private val mCtx: Context) {

    val FIRST_LOGIN = "isFirstTime"
    val IS_LOGGED_IN = "isLoggedIn"

    private fun getAsString(key: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getStringOrNull(key)
    }

    private fun getAsBoolean(key: String): Boolean {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key)
    }

    val isLoggedIn: Boolean
        get() {
            return getAsBoolean(IS_LOGGED_IN)
        }

    val isFirstTimeLogin: Boolean
        get() {
            return getAsBoolean(FIRST_LOGIN)
        }

    val getLoggedUserName: String
        get() {
            return getAsString("userName").toString()
        }
    val getLoggedUserPassword: String
        get() {
            return getAsString("password").toString()
        }
    val getLoggedUserId: UUID?
        get() {
            val id = getAsString("id")
            return if (id != null) {
                UUID.fromString(id)
            } else {
                null
            }
        }
    val fetchAuthToken: String?
        get() {
            val token = getAsString("bearerToken")
            return token
    }
    val getLoggedUserRoles: MutableSet<User.Role>
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val rolesString: Set<String>? = sharedPreferences.getStringSet("roles", emptySet())
            if (rolesString.isNullOrEmpty()) {
                return mutableSetOf(User.Role.PUBLIC)
            }
            val userRoles: MutableSet<User.Role> = mutableSetOf()
            for (i in rolesString) {
                userRoles.add(User.Role.valueOf(i))
            }
            return userRoles
        }

    val user: User?
        get() {
            return if (getLoggedUserId == null) {
                null
            } else {
                User(
                    getLoggedUserId,
                    getLoggedUserName,
                    getLoggedUserPassword,
                    getLoggedUserRoles
                )
            }
        }


    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("id", user.id.toString())
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.putStringSet("roles", user.roles.map { it.name }.toSet())
        editor.apply()
    }

    fun setAsLoggedIn() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_LOGIN, true)
        editor.apply()
    }

    fun savePreference(key: String, value: String) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "queimadasApp"
        private var mInstance: SharedPrefsManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefsManager {
            if (mInstance == null) {
                mInstance = SharedPrefsManager(mCtx)
            }
            return mInstance as SharedPrefsManager
        }
    }

}
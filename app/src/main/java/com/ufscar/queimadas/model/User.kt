package com.ufscar.queimadas.model

import com.google.gson.annotations.SerializedName
import java.util.*

// maybe mutable live data?
data class User(
    @SerializedName("id")
    var id: UUID? = null,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    var roles: MutableSet<Role> = mutableSetOf(Role.PUBLIC)
) {
    enum class Role(var displayValue: String) {
        PUBLIC("Public"), FIREFIGHTER("Firefighter"), SUPER_ADMIN("Super Admin")
    }
}
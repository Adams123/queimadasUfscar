package com.ufscar.queimadas.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
data class UserTokenAuth(
    @NotNull
    @PrimaryKey(autoGenerate = false)
    var userId: UUID,
    var token: String?
)
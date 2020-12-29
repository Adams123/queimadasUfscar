package com.ufscar.queimadas.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ufscar.queimadas.db.entity.UserTokenAuth
import com.ufscar.queimadas.model.User
import java.util.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: UserTokenAuth)


    @Query("SELECT * FROM UserTokenAuth")
    fun getAllUsers() : LiveData<List<UserTokenAuth>>

    @Query("SELECT * FROM UserTokenAuth where userId = :id")
    fun getUserById(id: UUID) : LiveData<UserTokenAuth>

}
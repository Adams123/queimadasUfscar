package com.ufscar.queimadas.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ufscar.queimadas.db.converters.UUIDConverter
import com.ufscar.queimadas.db.dao.UserDao
import com.ufscar.queimadas.db.entity.UserTokenAuth

@Database(
    entities = [UserTokenAuth::class],
    version = 1
)
@TypeConverters(UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserAuthDao() : UserDao

    companion object {
        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "UserTokenDB"
        ).build()
    }
}
package com.example.githubuserremake.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse

@Database(entities = [UserResponse::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        Define.EXTRA_TABLE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE as UserDatabase
        }
    }
}
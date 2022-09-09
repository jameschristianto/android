package com.example.githubuserremake.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuserremake.datasource.UserResponse

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY login ASC")
    fun getFavoriteList(): LiveData<List<UserResponse>>

    @Query("SELECT  * from user WHERE id = :id")
    fun getFavoriteById(id: Int): LiveData<UserResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteUser(user: UserResponse)

    @Delete
    fun deleteFavoriteUser(user: UserResponse)
}
package com.example.githubuserremake.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserremake.database.UserDao
import com.example.githubuserremake.database.UserDatabase
import com.example.githubuserremake.datasource.UserResponse
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteViewModel(application: Application) : ViewModel() {
    private val dao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val database: UserDatabase = UserDatabase.getInstance(application)
        dao = database.userDao()
    }

    fun getFavoriteList(): LiveData<List<UserResponse>> = dao.getFavoriteList()

    fun getFavoriteById(id: Int): LiveData<UserResponse> = dao.getFavoriteById(id)

    fun insertFavoriteUser(user: UserResponse) = executorService.execute {
        dao.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: UserResponse) = executorService.execute {
        dao.deleteFavoriteUser(user)
    }
}
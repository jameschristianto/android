package com.example.githubuserremake.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FollowerViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowerViewModel::class.java)) {
            return FollowerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
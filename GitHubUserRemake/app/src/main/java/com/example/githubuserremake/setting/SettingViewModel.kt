package com.example.githubuserremake.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuserremake.datasource.UserDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: UserDataStore) : ViewModel() {
    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        pref.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = pref.getThemeSetting().asLiveData(Dispatchers.IO)
}
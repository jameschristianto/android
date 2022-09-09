package com.example.githubuserremake.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserremake.databinding.ActivitySplashBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserDataStore
import com.example.githubuserremake.main.MainActivity
import com.example.githubuserremake.setting.SettingViewModel
import com.example.githubuserremake.setting.SettingViewModelFactory

class SplashActivity : AppCompatActivity() {
    private val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
        name = "settings"
    )
    private lateinit var binding: ActivitySplashBinding
    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        loadSetting()
    }

    private fun init() {
        val pref = UserDataStore.getInstance(dataStore)

        settingViewModel =
            ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]

        supportActionBar?.hide()
    }

    private fun loadSetting() {
        Handler(Looper.getMainLooper()).postDelayed({
            settingViewModel.getThemeSetting().observe(this@SplashActivity, { isDarkModeActive ->
                if (isDarkModeActive) {
                    run()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    run()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })
        }, Define.TIME_SPLASH)
    }

    private fun run() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}
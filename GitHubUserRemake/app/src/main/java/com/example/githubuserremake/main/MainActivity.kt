package com.example.githubuserremake.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubuserremake.R
import com.example.githubuserremake.adapter.UserAdapter
import com.example.githubuserremake.databinding.ActivityMainBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.detailuser.DetailUserActivity
import com.example.githubuserremake.favorite.FavoriteActivity
import com.example.githubuserremake.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        loadUser()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                if (query?.isNotEmpty() == true) {
                    showLoading(true)
                    mainViewModel.setSearchUser(query.toString())
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                val intentSetting = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intentSetting)
                true
            }
            R.id.favorite -> {
                val intentSetting = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intentSetting)
                true
            }
            else -> true
        }
    }

    private fun init() {
        val layoutManager = GridLayoutManager(this, 2)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
        if (!mainViewModel.isCall()) mainViewModel.setUserList()

        binding.rvUser.layoutManager = layoutManager
        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter
        binding.rvUser.setHasFixedSize(true)

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponse) {
                val intentProfile = Intent(this@MainActivity, DetailUserActivity::class.java)
                intentProfile.putExtra(Define.EXTRA_USER, data)
                startActivity(intentProfile)
            }
        })
    }

    private fun loadUser() {
        showLoading(true)
        mainViewModel.getUser().observe(this@MainActivity) { userItems ->
            if (userItems != null) {
                userAdapter.setListUser(userItems)
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarMain.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (view != null) imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
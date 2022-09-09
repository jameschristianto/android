package com.example.githubuserremake.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubuserremake.R
import com.example.githubuserremake.adapter.UserAdapter
import com.example.githubuserremake.databinding.ActivityFavoriteBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.detailuser.DetailUserActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        loadFavoriteUser()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        val layoutManager = GridLayoutManager(this, 2)
        val actionBar = supportActionBar

        favoriteViewModel =
            ViewModelProvider(
                this,
                FavoriteViewModelFactory(application)
            )[FavoriteViewModel::class.java]

        binding.rvFavorite.layoutManager = layoutManager
        favoriteAdapter = UserAdapter()
        binding.rvFavorite.adapter = favoriteAdapter
        binding.rvFavorite.setHasFixedSize(true)

        favoriteAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponse) {
                val intentProfile = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                intentProfile.putExtra(Define.EXTRA_USER, data)
                startActivity(intentProfile)
            }
        })

        title = resources.getString(R.string.favorite_user)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadFavoriteUser() {
        showLoading(true)
        favoriteViewModel.getFavoriteList().observe(this@FavoriteActivity) { favoriteItems ->
            if (favoriteItems != null) {
                favoriteAdapter.setListUser(favoriteItems as ArrayList<UserResponse>)
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFavorite.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
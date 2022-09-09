package com.example.githubuserremake.detailuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserremake.R
import com.example.githubuserremake.adapter.PagerAdapter
import com.example.githubuserremake.databinding.ActivityDetailUserBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.favorite.FavoriteViewModel
import com.example.githubuserremake.favorite.FavoriteViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var user: UserResponse
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        loadData()
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
        val sectionsPagerAdapter = PagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        val actionBar = supportActionBar

        user = intent.getParcelableExtra<UserResponse>(Define.EXTRA_USER) as UserResponse

        favoriteViewModel =
            ViewModelProvider(
                this,
                FavoriteViewModelFactory(application)
            )[FavoriteViewModel::class.java]

        detailUserViewModel =
            ViewModelProvider(
                this,
                DetailUserViewModelFactory()
            )[DetailUserViewModel::class.java]

        binding.imgFavorite.setOnClickListener {
            isFavorite = if (isFavorite) {
                favoriteViewModel.deleteFavoriteUser(user)
                false
            } else {
                favoriteViewModel.insertFavoriteUser(user)
                true
            }
        }

        sectionsPagerAdapter.setUsername(user.login.toString())
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(Define.TAB_TITLES[position])
        }.attach()

        title = resources.getString(R.string.profile)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadData() {
        showLoading(true)
        favoriteViewModel.getFavoriteById(user.id!!)
            .observe(this@DetailUserActivity) { userFavorite ->
                isFavorite = if (userFavorite == null) {
                    binding.imgFavorite.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_baseline_favorite_border_24,
                            null
                        )
                    )
                    false
                } else {
                    binding.imgFavorite.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_baseline_favorite_24,
                            null
                        )
                    )
                    true
                }
            }
        detailUserViewModel.getDetailUser(user.login.toString())
            .observe(this@DetailUserActivity) { userDetail ->
                with(binding) {
                    Glide.with(root)
                        .load(userDetail.avatarUrl)
                        .circleCrop()
                        .into(imgAvatarProfile)
                    tvNameProfile.text = userDetail.name ?: "-"
                    tvUsernameProfile.text = userDetail.login ?: "-"
                    tvBioProfile.text = userDetail.bio ?: "-"
                    tvNumberFollowerProfile.text = userDetail.followers.toString()
                    tvNumberFollowingProfile.text = userDetail.following.toString()
                }
                showLoading(false)
            }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarDetailUser.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
package com.example.githubuserremake.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.follower.FollowerFragment
import com.example.githubuserremake.following.FollowingFragment

class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private lateinit var username: String

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowerFragment()
                val bundle = Bundle()
                bundle.putString(Define.EXTRA_USERNAME, username)
                fragment.arguments = bundle
            }
            1 -> {
                fragment = FollowingFragment()
                val bundle = Bundle()
                bundle.putString(Define.EXTRA_USERNAME, username)
                fragment.arguments = bundle
            }
        }

        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return Define.TAB_TITLES.size
    }

    fun setUsername(dataUsername: String) {
        username = dataUsername
    }
}
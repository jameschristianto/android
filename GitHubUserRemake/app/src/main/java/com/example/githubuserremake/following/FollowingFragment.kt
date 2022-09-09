package com.example.githubuserremake.following

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubuserremake.adapter.UserAdapter
import com.example.githubuserremake.databinding.FragmentFollowingBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.detailuser.DetailUserActivity

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingAdapter: UserAdapter
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        loadFollowing()
    }

    private fun init() {
        ViewModelProvider(this, FollowingViewModelFactory())
            .get(FollowingViewModel::class.java).also { followingViewModel = it }

        binding.rvFollowing.layoutManager = GridLayoutManager(requireContext(), 2)
        followingAdapter = UserAdapter()
        binding.rvFollowing.adapter = followingAdapter
        binding.rvFollowing.setHasFixedSize(true)

        followingAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponse) {
                val intentProfile = Intent(context, DetailUserActivity::class.java)
                intentProfile.putExtra(Define.EXTRA_USER, data)
                startActivity(intentProfile)
            }
        })
    }

    private fun loadFollowing() {
        showLoading(true)
        followingViewModel.getFollowingList(arguments?.getString(Define.EXTRA_USERNAME).toString())
            .observe(viewLifecycleOwner) { followingItems ->
                if (followingItems != null) {
                    followingAdapter.setListUser(followingItems)
                    showLoading(false)
                }
            }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFollowing.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
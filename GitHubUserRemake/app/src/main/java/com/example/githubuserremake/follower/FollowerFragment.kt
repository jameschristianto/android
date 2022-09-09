package com.example.githubuserremake.follower

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubuserremake.adapter.UserAdapter
import com.example.githubuserremake.databinding.FragmentFollowerBinding
import com.example.githubuserremake.datasource.Define
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.detailuser.DetailUserActivity

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var followerAdapter: UserAdapter
    private lateinit var followerViewModel: FollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        loadFollower()
    }

    private fun init() {
        ViewModelProvider(this, FollowerViewModelFactory())
            .get(FollowerViewModel::class.java).also { followerViewModel = it }

        binding.rvFollower.layoutManager = GridLayoutManager(requireContext(), 2)
        followerAdapter = UserAdapter()
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.setHasFixedSize(true)

        followerAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponse) {
                val intentProfile = Intent(context, DetailUserActivity::class.java)
                intentProfile.putExtra(Define.EXTRA_USER, data)
                startActivity(intentProfile)
            }
        })
    }

    private fun loadFollower() {
        showLoading(true)
        followerViewModel.getFollowerList(arguments?.getString(Define.EXTRA_USERNAME).toString())
            .observe(viewLifecycleOwner) { followerItems ->
                if (followerItems != null) {
                    followerAdapter.setListUser(followerItems)
                    showLoading(false)
                }
            }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFollower.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
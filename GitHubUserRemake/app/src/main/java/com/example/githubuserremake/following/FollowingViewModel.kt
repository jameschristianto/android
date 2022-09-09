package com.example.githubuserremake.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    private val followingList = MutableLiveData<ArrayList<UserResponse>>()

    fun getFollowingList(login: String): LiveData<ArrayList<UserResponse>> {
        ApiConfig.getApiService().getFollowingList(login).enqueue(object :
            Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                val list = response.body() as ArrayList<UserResponse>
                followingList.postValue(list)
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {

            }
        })

        return followingList
    }
}
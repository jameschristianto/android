package com.example.githubuserremake.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    private val followerList = MutableLiveData<ArrayList<UserResponse>>()

    fun getFollowerList(login: String): LiveData<ArrayList<UserResponse>> {
        ApiConfig.getApiService().getFollowerList(login).enqueue(object :
            Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                val list = response.body() as ArrayList<UserResponse>
                followerList.postValue(list)
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {

            }
        })

        return followerList
    }
}
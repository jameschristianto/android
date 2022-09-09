package com.example.githubuserremake.main

import androidx.lifecycle.*
import com.example.githubuserremake.datasource.SearchResponse
import com.example.githubuserremake.datasource.UserResponse
import com.example.githubuserremake.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val userList = MutableLiveData<ArrayList<UserResponse>>()
    private var isCall = false

    fun setUserList() {
        ApiConfig.getApiService().getUserList().enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                val list = response.body() as ArrayList<UserResponse>
                userList.postValue(list)
                isCall = true
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {

            }
        })
    }

    fun setSearchUser(query: String) {
        ApiConfig.getApiService().searchlUser(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val list = response.body()?.items as ArrayList<UserResponse>
                userList.postValue(list)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

            }
        })
    }

    fun getUser(): LiveData<ArrayList<UserResponse>> = userList

    fun isCall(): Boolean = isCall
}
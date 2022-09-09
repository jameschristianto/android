package com.example.githubuserremake.networking

import com.example.githubuserremake.BuildConfig
import com.example.githubuserremake.datasource.DetailUserResponse
import com.example.githubuserremake.datasource.SearchResponse
import com.example.githubuserremake.datasource.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getUserList(): Call<ArrayList<UserResponse>>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun searchlUser(@Query("q") username: String?): Call<SearchResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowerList(@Path("username") username: String): Call<ArrayList<UserResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowingList(@Path("username") username: String): Call<ArrayList<UserResponse>>
}
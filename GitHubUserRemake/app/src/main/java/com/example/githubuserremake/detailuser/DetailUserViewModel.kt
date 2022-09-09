package com.example.githubuserremake.detailuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserremake.datasource.DetailUserResponse
import com.example.githubuserremake.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val detailUser = MutableLiveData<DetailUserResponse>()

    fun getDetailUser(login: String): LiveData<DetailUserResponse> {
        ApiConfig.getApiService().getDetailUser(login)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    val list = response.body() as DetailUserResponse
                    detailUser.postValue(list)
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {

                }
            })

        return detailUser
    }
}

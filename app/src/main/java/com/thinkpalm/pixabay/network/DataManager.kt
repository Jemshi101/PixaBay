package com.thinkpalm.pixabay.network

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thinkpalm.pixabay.core.Config
import com.thinkpalm.pixabay.network.models.APIResponseBean
import com.thinkpalm.pixabay.network.models.login.LoginRequest
import com.thinkpalm.pixabay.network.models.login.LoginResponse
import com.thinkpalm.pixabay.network.models.photolist.PhotoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Jemsheer K D on 19 December, 2018.
 * Package com.thinkpalm.pixabay.network
 * Project RoyalArabianTours
 */
class DataManager {

    companion object {

        fun getAPI(): API = ApiClient.getInstance().api

        var unauthorizedAccessStatus: MutableLiveData<Boolean> = MutableLiveData()


       /* @JvmStatic
        fun fetchDummy(): LiveData<APIResponseBean<PhotoListResponse>> {
            var responseData = MutableLiveData<APIResponseBean<PhotoListResponse>>()

            val call = getAPI().fetchDummy(getAuthorization(), getCurrency())
            call.enqueue(object : Callback<PhotoListResponse> {
                override fun onResponse(
                    call: Call<PhotoListResponse>,
                    response: Response<PhotoListResponse>
                ) {
                    if (response != null) {
                        if (response.code() == 401) {
                            unauthorizedAccessStatus.postValue(true)
                        } else {
                            responseData.postValue(APIResponseBean(true, response.body()))
                        }
                    } else
                        responseData.postValue(APIResponseBean(false, null))
                }

                override fun onFailure(call: Call<PhotoListResponse>, t: Throwable) {
                    responseData.postValue(APIResponseBean(false, null))
                }
            })
            return responseData
        }*/

        @JvmStatic
        fun performLogin(loginRequest: LoginRequest): LiveData<APIResponseBean<LoginResponse>> {
            var responseData = MutableLiveData<APIResponseBean<LoginResponse>>()

            if (!Config.isDemo) {
                val call = getAPI().performLogin(loginRequest)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response != null) {
                            if (response.code() == 401) {
                                unauthorizedAccessStatus.postValue(true)
                            } else {
                                responseData.postValue(APIResponseBean(true, response.body()))
                            }
                        } else
                            responseData.postValue(APIResponseBean(false, null))
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        responseData.postValue(APIResponseBean(false, null))
                    }
                })
            } else {
                Handler().postDelayed({
                    responseData.postValue(APIResponseBean(true, LoginResponse()))
                }, 1000)
            }
            return responseData
        }

        @JvmStatic
        fun fetchPhotoList(query: String): LiveData<APIResponseBean<PhotoListResponse>> {
            var responseData = MutableLiveData<APIResponseBean<PhotoListResponse>>()

            val call = getAPI().getPhotoList(ApiUrl.API_KEY, query, ApiUrl.IMAGE_TYPE)
            call.enqueue(object : Callback<PhotoListResponse> {
                override fun onResponse(
                    call: Call<PhotoListResponse>,
                    response: Response<PhotoListResponse>
                ) {
                    if (response != null) {
                        if (response.code() == 401) {
                            unauthorizedAccessStatus.postValue(true)
                        } else {
                            responseData.postValue(APIResponseBean(true, response.body()))
                        }
                    } else
                        responseData.postValue(APIResponseBean(false, null))
                }

                override fun onFailure(call: Call<PhotoListResponse>, t: Throwable) {
                    responseData.postValue(APIResponseBean(false, null))
                }
            })
            return responseData
        }
    }

}

package com.example.spacex.activities.apis.response.interfaces

import com.example.spacex.activities.apis.response.RocketsListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("v4/rockets")
    fun getRocketList() : Call<RocketsListResponse>
}
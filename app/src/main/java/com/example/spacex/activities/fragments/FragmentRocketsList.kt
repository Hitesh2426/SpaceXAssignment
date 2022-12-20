package com.example.spacex.activities.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spacex.R
import com.example.spacex.activities.RoomDataBase.RocketListViewModel
import com.example.spacex.activities.adapter.RocketListAdapter
import com.example.spacex.activities.apis.response.RocketsListResponse
import com.example.spacex.activities.apis.response.RocketsListResponseItem
import com.example.spacex.activities.apis.response.interfaces.ApiInterface
import com.example.spacex.databinding.FragmentRocketsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentRocketsList : Fragment() {
    private lateinit var binding: FragmentRocketsListBinding
    private lateinit var rocketsListResponse: RocketsListResponse
    private lateinit var rocketListAdapter: RocketListAdapter
    private val rocketListViewModel : RocketListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rockets_list, container, false)
        getRocketList()
        return binding.root
    }

    fun getRocketList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiInterface::class.java)
        val call = service.getRocketList()
        call.enqueue(object : Callback<RocketsListResponse> {
            override fun onResponse(
                call: Call<RocketsListResponse>,
                response: Response<RocketsListResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Toast.makeText(context,"Data fetch successfully",Toast.LENGTH_SHORT).show()
                        Log.e("API RESPONSE", response.body().toString())
                        val rocketsListResponse = response.body()
                   //     rocketListViewModel.insertData(rocketsListResponse)
                        binding.shimmerMainContent.visibility = View.GONE
                        binding.rocketsList.visibility = View.VISIBLE
                        rocketListAdapter = RocketListAdapter(rocketsListResponse,context)
                        binding.rocketsList.adapter = rocketListAdapter
                        rocketListAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<RocketsListResponse>, t: Throwable) {
                Log.e("error","Api Failed",t)
            }

        })
    }

    companion object {
        var BaseUrl = "https://api.spacexdata.com"
    }

}
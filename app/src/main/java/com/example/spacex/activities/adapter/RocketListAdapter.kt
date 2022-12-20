package com.example.spacex.activities.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.activities.apis.response.RocketsListResponse
import com.example.spacex.databinding.AdapterRocketsListBinding
import java.util.ArrayList

class RocketListAdapter(
   private var rocketsListResponse: RocketsListResponse?,
   private var context: Context?
) : RecyclerView.Adapter<RocketListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: AdapterRocketsListBinding) : RecyclerView.ViewHolder(binding.root) {

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterRocketsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(rocketsListResponse?.get(position) ?: rocketsListResponse){
            binding.nameTxt.text = rocketsListResponse?.get(position)?.name ?: "Falcon 9"
            binding.countryTxt.text = rocketsListResponse?.get(position)?.country ?: "USA"
            binding.engineCountTxt.text = rocketsListResponse?.get(position)?.second_stage?.engines.toString()
                context?.let {
                    Glide.with(it)
                        .load(rocketsListResponse?.get(position)?.flickr_images?.get(position).toString())
                        .into(binding.rocketImg)
                }
                binding.getDetailsBtn.setOnClickListener{
                    val bundle = Bundle()
                    bundle.putString("name", rocketsListResponse?.get(position)?.name)
                    bundle.putString("description", rocketsListResponse?.get(position)?.description)
                    bundle.putString("wikipedia", rocketsListResponse?.get(position)?.wikipedia)
                    bundle.putStringArrayList("images",
                        rocketsListResponse?.get(position)?.flickr_images as ArrayList<String>?
                    )
                    rocketsListResponse?.get(position)?.cost_per_launch?.let { it1 ->
                        bundle.putInt("cost",
                            it1
                        )
                    }
                    rocketsListResponse?.get(position)?.success_rate_pct?.let { it1 ->
                        bundle.putInt("success",
                            it1
                        )
                    }
                    rocketsListResponse?.get(position)?.active?.let { it1 ->
                        bundle.putBoolean("active",
                            it1
                        )
                    }
                    rocketsListResponse?.get(position)?.height?.feet?.let { it1 ->
                        bundle.putDouble("height",
                            it1
                        )
                    }

                    rocketsListResponse?.get(position)?.diameter?.feet?.let { it1 ->
                        bundle.putDouble("diameter",
                            it1 as Double
                        )
                    }
                  //  bundle.putBundle("images", rocketsListResponse?.get(position)?.flickr_images)
                    Navigation.findNavController(binding.root).navigate(R.id.fragmentRocketsDetails,bundle)
                }
           }
        }
    }

    override fun getItemCount(): Int {
     return rocketsListResponse?.size ?: 5
    }

}


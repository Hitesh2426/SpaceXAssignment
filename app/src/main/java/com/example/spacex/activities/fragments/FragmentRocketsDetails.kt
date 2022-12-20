package com.example.spacex.activities.fragments

import android.R.attr.data
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.spacex.R
import com.example.spacex.activities.adapter.ItemsImageAdapter
import com.example.spacex.activities.apis.response.RocketsListResponse
import com.example.spacex.databinding.FragmentRocketsDetailsBinding
import java.util.ArrayList


class FragmentRocketsDetails : Fragment() {
    private lateinit var binding : FragmentRocketsDetailsBinding
    lateinit var itemsImageAdapter: ItemsImageAdapter
    lateinit var rocketsListResponse: RocketsListResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rockets_details, container, false)
        binding.headerView.titleTv.text = "Details"
        binding.statusCheck.isChecked = arguments?.getBoolean("active") == true
        binding.nameHeadTxt.text = arguments?.getString("name")
        binding.descriptionTxt.text = arguments?.getString("description")
        binding.heightTxt.text = arguments?.getDouble("height").toString()
        binding.diameterTxt.text = arguments?.getDouble("diameter").toString()
        binding.wikipediaTxt.text = arguments?.getString("wikipedia")
        binding.wikipediaTxt.setMovementMethod(LinkMovementMethod.getInstance());
        binding.costTxt.text = arguments?.getInt("cost").toString()
        binding.successRateTxt.text = (arguments?.getInt("success").toString())
        val imageUrlList: List<String> =  arguments?.getStringArrayList("images") as List<String>
        itemsImageAdapter = ItemsImageAdapter(imageUrlList)
        binding.viewPager.adapter = itemsImageAdapter
        return binding.root
    }
}
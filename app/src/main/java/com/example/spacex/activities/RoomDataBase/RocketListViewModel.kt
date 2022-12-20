package com.example.spacex.activities.RoomDataBase

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.activities.apis.response.RocketsListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RocketListViewModel: ViewModel() {

    private val context :Context = TODO()
    fun insertData(model: RocketsListResponse?){
        GlobalScope.launch {
            getDatabase(context).appDao().insertItems(model)
        }
    }

}


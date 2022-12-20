package com.example.spacex.activities.RoomDataBase

import androidx.lifecycle.LiveData
import com.example.spacex.activities.apis.response.RocketsListResponse
import com.example.spacex.activities.apis.response.RocketsListResponseItem
import com.example.spacex.activities.apis.response.interfaces.DaoInterface
import javax.inject.Inject

class RocketsListRepo @Inject constructor(private val daoInterface: DaoInterface) {

    fun createRocketList(responseItem: RocketsListResponse) {
        return daoInterface.insertItems(responseItem)
    }

    val getAllData: LiveData<List<RocketsListResponse>> get() = daoInterface.getAllDataSet()

    fun deleteRocketList() {
        daoInterface.deleteAll()
    }
}
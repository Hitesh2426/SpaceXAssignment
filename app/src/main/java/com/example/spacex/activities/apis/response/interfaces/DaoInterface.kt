package com.example.spacex.activities.apis.response.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacex.activities.apis.response.RocketsListResponse
import com.example.spacex.activities.apis.response.RocketsListResponseItem

@Dao
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(rocketsListResponseItem: RocketsListResponse?)

    @Query("SELECT * FROM ROCKETSLISTRESPONSEITEM")
    fun getAllDataSet(): LiveData<List<RocketsListResponse>>

    @Query("DELETE FROM RocketsListResponseItem")
    fun deleteAll()

}
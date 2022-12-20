package com.example.spacex.activities.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacex.activities.apis.response.RocketsListResponseItem
import com.example.spacex.activities.apis.response.interfaces.DaoInterface

@Database(entities = [RocketsListResponseItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao(): DaoInterface
}

private lateinit var INSTANCE : AppDatabase

fun getDatabase(context: Context): AppDatabase {

    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database"
            ).build()
        }
    }

    return INSTANCE
}
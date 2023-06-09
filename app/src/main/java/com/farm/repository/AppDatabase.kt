package com.farm.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(exportSchema = true, entities = [FieldEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fieldDao() : FieldDao

    companion object {
        @Synchronized
        fun getDataBase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, "IT-jet"
            ).build()
        }
    }
}
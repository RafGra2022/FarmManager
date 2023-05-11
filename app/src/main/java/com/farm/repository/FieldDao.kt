package com.farm.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FieldDao {

    @Insert
    fun insert(fieldEntity: FieldEntity)

    @Query("Select * from field")
    suspend fun findAllFields() : List<FieldEntity>

}
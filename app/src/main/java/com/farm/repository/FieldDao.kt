package com.farm.repository

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface FieldDao {

    @Insert
    fun insert(fieldEntity: FieldEntity);

}
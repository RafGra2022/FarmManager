package com.farm.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "field")
data class FieldEntity(

    @PrimaryKey(autoGenerate = true) val id : Long?,
    val field : String,
    val area : Float,
    val unit : String,
    @ColumnInfo("insstmp")
    val insertStamp : String
)
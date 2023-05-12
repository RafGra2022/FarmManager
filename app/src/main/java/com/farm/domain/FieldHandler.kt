package com.farm.domain

import android.content.Context
import com.farm.repository.AppDatabase
import com.farm.repository.FieldEntity
import com.farm.utlis.SystemTimeStamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldHandler {

    fun save(context: Context?, fieldDetail: FieldDetail) {
        val fieldEntity = FieldEntity(
            null,
            fieldDetail.field,
            fieldDetail.area,
            fieldDetail.unit,
            SystemTimeStamp.getSystemTime()
        )
        if (context != null) {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDataBase(context).fieldDao().insert(fieldEntity)
            }
        }
    }

    suspend fun fetchAllFields(context: Context?) : ArrayList<FieldDetail>{
        if (context != null) {
                val fields = AppDatabase.getDataBase(context).fieldDao().findAllFields() as ArrayList
                return mapToFieldDetail(fields)
        }
        return ArrayList()
    }

    private fun mapToFieldDetail(fieldEntities : ArrayList<FieldEntity>) : ArrayList<FieldDetail>{
        val fields : ArrayList<FieldDetail> = ArrayList()
        for(fieldEntity in fieldEntities){
            fields.add(FieldDetail(fieldEntity.field,fieldEntity.area,fieldEntity.unit))
        }
        return fields
    }
}
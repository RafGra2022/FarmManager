package com.farm.domain

import android.content.Context
import com.farm.repository.AppDatabase
import com.farm.repository.FieldEntity
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
            fieldDetail.insertStamp
        )
        if (context != null) {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDataBase(context).fieldDao().insert(fieldEntity)
            }
        }
    }

}
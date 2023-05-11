package com.farm.utlis

import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class SystemTimeStamp {

    companion object{
        fun getSystemTime() : String{
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), TimeZone.getDefault().toZoneId()).format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        }
    }
}
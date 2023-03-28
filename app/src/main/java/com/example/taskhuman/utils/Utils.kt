package com.example.taskhuman.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun convertTimeStampToDateString(timeStamp: Long): String {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            val date = Date(timeStamp)
            return dateFormat.format(Date())
        }
    }
}
package com.example.resumeviewer.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProjectTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToListOfStrings(data: String?): List<String> {
        data?.let {
            val listType = object : TypeToken<List<String>>(){}.type
            return gson.fromJson(data, listType)
        }
        return emptyList()
    }

    @TypeConverter
    fun listOfStringsToString(data: List<String>): String {
        return gson.toJson(data)
    }
}
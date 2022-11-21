package com.locathor.brzodolokacije.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.locathor.brzodolokacije.domain.model.Post
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class RoomConverters @Inject constructor(){
    @TypeConverter
    fun fromListOfString(value: List<String>): String{
        val listOfStringType = Types.newParameterizedType(List::class.java, String::class.java)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<String>>(listOfStringType)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toListOfString(value: String): List<String>{
        val listOfStringType = Types.newParameterizedType(List::class.java, String::class.java)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<String>>(listOfStringType)
        return adapter.fromJson(value)!!
    }
}
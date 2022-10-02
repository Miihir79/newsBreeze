package com.mihir.newsbreeze.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mihir.newsbreeze.data.model.Source
class TypeConverter {
    @TypeConverter
    fun classToJson(source: Source?):String{
        return Gson().toJson(source)
    }

    @TypeConverter
    fun classFromJson(source: String?):Source{
        return Gson().fromJson(source,Source::class.java)
    }
}


package com.mihir.newsbreeze.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mihir.newsbreeze.data.model.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun NewsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDatabase?=null

        fun getDatabase(context: Context): NewsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
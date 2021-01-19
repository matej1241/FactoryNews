package com.matej.factorynews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matej.factorynews.model.NewsDb

@Database(entities = [NewsDb::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, NewsDatabase::class.java, "NewsDatabase")
                        .allowMainThreadQueries().build()
            }
            return instance as NewsDatabase
        }


    }

}
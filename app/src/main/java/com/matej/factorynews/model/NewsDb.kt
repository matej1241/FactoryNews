package com.matej.factorynews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsDb (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "urlToImage") val urlToImage: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "timeStamp") val timeStamp: Long = 0
)
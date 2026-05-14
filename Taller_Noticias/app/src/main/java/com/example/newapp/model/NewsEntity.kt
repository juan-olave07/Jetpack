package com.example.newapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define la tabla "news_table" en la base de datos Room
@Entity(tableName = "news_table")
data class NewsEntity(

    // Clave primaria generada automáticamente
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val title: String,
    val content: String?,
    val author: String?,
    val url: String,
    val urlToImage: String?
)
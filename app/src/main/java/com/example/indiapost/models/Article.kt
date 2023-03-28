package com.example.indiapost.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Article(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int? = null,
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val url: String,
    val urlToImage: String
)
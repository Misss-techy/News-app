package com.example.indiapost.models

data class Article(
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val url: String,
    val urlToImage: String
)
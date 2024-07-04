package com.example.movielistingapp.model

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val runningTime: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false
)


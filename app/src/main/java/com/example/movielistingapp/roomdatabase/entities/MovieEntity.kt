package com.example.movielistingapp.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val runningTime: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false
)

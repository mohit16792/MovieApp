package com.example.movielistingapp.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val movieId: Int,
    val commentText: String
)

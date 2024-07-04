package com.example.movielistingapp.repository

import com.example.movielistingapp.roomdatabase.entities.CommentEntity
import com.example.movielistingapp.roomdatabase.entities.MovieEntity
import com.example.movielistingapp.roomdatabase.movieDao.MovieDao
import kotlinx.coroutines.flow.Flow


class MovieRepository(private val movieDao: MovieDao) {

    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getMovieById(id: Int): Flow<MovieEntity?> = movieDao.getMovieById(id)

    suspend fun addComment(comment: CommentEntity) {
        movieDao.insertComment(comment)
    }

    fun getComments(movieId: Int): Flow<List<CommentEntity>> = movieDao.getCommentsByMovieId(movieId)

    suspend fun updateFavoriteStatus(movie: MovieEntity) {
        movieDao.updateFavoriteStatus(movie.id, !movie.isFavorite)
    }

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()
}


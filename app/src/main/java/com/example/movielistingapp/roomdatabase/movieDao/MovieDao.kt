package com.example.movielistingapp.roomdatabase.movieDao

import androidx.room.*
import com.example.movielistingapp.roomdatabase.entities.CommentEntity
import com.example.movielistingapp.roomdatabase.entities.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Query("SELECT * FROM comments WHERE movieId = :movieId")
    fun getCommentsByMovieId(movieId: Int): Flow<List<CommentEntity>>

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
}


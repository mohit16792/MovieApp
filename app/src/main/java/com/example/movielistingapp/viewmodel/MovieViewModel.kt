package com.example.movielistingapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movielistingapp.repository.MovieRepository
import com.example.movielistingapp.roomdatabase.entities.CommentEntity
import com.example.movielistingapp.roomdatabase.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies: Flow<List<MovieEntity>> = repository.getMovies()

    fun getMovieById(id: Int): Flow<MovieEntity?> = repository.getMovieById(id)

    fun addComment(comment: CommentEntity) {
        viewModelScope.launch {
            repository.addComment(comment)
        }
    }

    fun getComments(movieId: Int): Flow<List<CommentEntity>> = repository.getComments(movieId)

    fun updateFavoriteStatus(movie: MovieEntity) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(movie)
        }
    }

    val favoriteMovies: Flow<List<MovieEntity>> = repository.getFavoriteMovies()
}

class MovieViewModelFactory(val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

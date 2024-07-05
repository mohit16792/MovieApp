package com.example.movielistingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movielistingapp.databinding.FragmentMovieDetailBinding
import com.example.movielistingapp.repository.MovieRepository
import com.example.movielistingapp.roomdatabase.AppDatabase
import com.example.movielistingapp.roomdatabase.entities.CommentEntity
import com.example.movielistingapp.roomdatabase.entities.MovieEntity
import com.example.movielistingapp.viewmodel.MovieViewModel
import com.example.movielistingapp.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var viewModel: MovieViewModel
    private lateinit var movie: MovieEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = AppDatabase.getInstance(requireContext())
        val repository = MovieRepository(database.movieDao())
        val factory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieById(args.movieId.toInt()).collect { movieEntity ->
                movieEntity?.let {
                    movie = it
                    binding.movie = movie
                }
            }
        }

        binding.btnAddComment.setOnClickListener {
            val commentText = binding.etComment.text.toString()
            if (commentText.isNotEmpty()) {
                viewModel.addComment(CommentEntity(0, movie.id, commentText))
                binding.etComment.text.clear()
            }
        }

        binding.btnFavorite.setOnClickListener {
            movie.isFavorite = !movie.isFavorite
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.updateFavoriteStatus(movie)
                binding.btnFavorite.text = if (movie.isFavorite) "Unfavorite" else "Favorite"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

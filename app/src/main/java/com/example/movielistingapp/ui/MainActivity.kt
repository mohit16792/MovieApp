package com.example.movielistingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movielistingapp.R
import com.example.movielistingapp.repository.MovieRepository
import com.example.movielistingapp.roomdatabase.AppDatabase
import com.example.movielistingapp.roomdatabase.movieDao.MovieDao
import com.example.movielistingapp.viewmodel.MovieViewModel
import com.example.movielistingapp.viewmodel.MovieViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var movieDao: MovieDao
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       navController = findNavController(R.id.nav_host_fragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        val database = AppDatabase.getInstance(applicationContext)
        movieDao = database.movieDao()
        val repository = MovieRepository(database.movieDao())
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
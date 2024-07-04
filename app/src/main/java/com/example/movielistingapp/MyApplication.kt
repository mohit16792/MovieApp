package com.example.movielistingapp

import android.app.Application
import androidx.room.Room
import com.example.movielistingapp.roomdatabase.AppDatabase

//class MyApplication : Application() {
//
//    companion object {
//        lateinit var database: AppDatabase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        database = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "movie_database"
//        ).build()
//    }
//}
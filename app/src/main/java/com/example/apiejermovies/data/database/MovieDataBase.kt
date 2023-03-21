package com.example.apiejermovies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BDEntity::class], version = 1)
abstract class  MovieDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private const val DATABASE_NAME = "movie_database"
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getInstance(context: Context): MovieDataBase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}
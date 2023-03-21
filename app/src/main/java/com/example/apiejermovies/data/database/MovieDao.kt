package com.example.apiejermovies.data.database

import androidx.room.*
import com.example.apiejermovies.data.model.MoviesJson


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table ")
    suspend fun getAllMovies():List<BDEntity>

    @Query("SELECT * FROM movies_table WHERE")
    suspend fun getMovies(): MovieDataBase

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertAll(movies:List<BDEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(movie:MoviesJson)

    @Query("DELETE * FROM movies_table WHERE  movie : movie ")
    suspend fun deteleMovie(movie: MoviesJson)


    @Query("DELETE * FROM movies_table")
    suspend fun deteleAllMovie()

}
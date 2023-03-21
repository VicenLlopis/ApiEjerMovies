package com.example.apiejermovies.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiejermovies.R
import com.example.apiejermovies.data.ThemoviedbApi
import com.example.apiejermovies.ui.adapter.RecyclerAdapter
import com.example.apiejermovies.data.model.Movies
import com.example.apiejermovies.data.model.MoviesJson
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    var mAdapter: RecyclerAdapter = RecyclerAdapter()
    var isLoading : Boolean = true

    // Constructor retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val viewModel: ModelViewMainActivity by viewModels{ ModelViewModelFactory(emptyList()) }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplas = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            screenSplas.setKeepOnScreenCondition{
                //makeTheCall()
                isLoading
            }
        makeTheCall()
    }

    /**
     *Llama a la api que recoge las peliculas populares
     */
    fun makeTheCall() {
        val listMovies = mutableListOf<Movies>()
        val themoviedbApi = retrofit.create(ThemoviedbApi::class.java)
        val call =
            themoviedbApi.obtenerPopularPelicula("d570492bc43d67e4c3f4553d4e8a8d8e", "es-ES", 1)
        call.enqueue(object : Callback<MoviesJson> {
            override fun onResponse(call: Call<MoviesJson>, response: Response<MoviesJson>) {
                if (response.isSuccessful) {
                    val pelicula = response.body()
                    if (pelicula != null) {
                        pelicula.results.forEach { movie ->

                            listMovies.add(
                                Movies(
                                    title = movie.title,
                                    image = movie.backdrop_path,
                                    rating = movie.vote_average,
                                    forAdult = movie.adult,
                                    description = movie.overview
                                )
                            )
                            setUpRecyclerView(listMovies)
                            isLoading = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MoviesJson>, t: Throwable) {
                Log.d("Fallo","Ha fallado la llamada a la api ")
            }
        })

    }



    /**
     *Set up que prepara el recycled view con las movies.
     */

    fun setUpRecyclerView(lista: MutableList<Movies>) {

        mRecyclerView = findViewById(R.id.rvMovies)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mAdapter.RecyclerAdapter(lista, this)
        mRecyclerView.adapter = mAdapter
    }

}
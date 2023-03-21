package com.example.apiejermovies.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.apiejermovies.R

const val MOVIE_FOTO ="extra_foto"
const val MOVIE_NAME ="extra_nombre"
const val MOVIE_POPLARITY = "extra_popularity"
const val MOVIE_DESCRIPTION ="extra_description"
const val MOVIE_ADULT = "extra_adult"

class DetailActivity : AppCompatActivity() {

    private lateinit var foto : ImageView
    private lateinit var nombre : TextView
    private lateinit var popularity : TextView
    private lateinit var forAdults : TextView
    private lateinit var description : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        foto= findViewById(R.id.ivFoto)
        nombre = findViewById(R.id.tvTitulo)
        popularity = findViewById(R.id.tvPuntosMedios)
        forAdults = findViewById(R.id.tvAdult)
        description = findViewById(R.id.tvDescripcio)

        val extras = intent.extras

        if (extras != null) {
            populateDetails(extras)
        } else {
        }
    }
    private fun populateDetails(extras: Bundle) {
        extras.getString(MOVIE_FOTO)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(foto)
        }

        nombre.text = extras.getString(MOVIE_NAME)
        popularity.text= extras.getDouble(MOVIE_POPLARITY).toString()
        forAdults.text = extras.getString(MOVIE_ADULT)
        description.text = extras.getString(MOVIE_DESCRIPTION)

    }
}
package com.example.apiejermovies.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiejermovies.*
import com.example.apiejermovies.data.model.Movies
import com.example.apiejermovies.ui.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var movies: MutableList<Movies>  = ArrayList()
    lateinit var context:Context
    fun RecyclerAdapter(movies : MutableList<Movies>, context: Context){
        this.movies = movies
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = movies.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movies, parent, false))
    }
    override fun getItemCount(): Int {
        return movies.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.textView4) as TextView
        val image = view.findViewById(R.id.imageview) as ImageView
        fun bind(movie: Movies, context: Context) {
            title.text = movie.title
            image.loadUrl(movie.image)
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(MOVIE_NAME,movie.title)
                intent.putExtra("extra_foto",movie.image)
                intent.putExtra(MOVIE_DESCRIPTION,movie.description)
                intent.putExtra(MOVIE_POPLARITY, movie.rating)
                if(movie.forAdult==true){
                    intent.putExtra(MOVIE_ADULT,"Es una pelicula +18")
                }else if(!movie.forAdult){
                    intent.putExtra(MOVIE_ADULT,"Es una pelicula para todos los Publicos")
                }
               context.startActivity(intent)
            }
        }

        fun ImageView.loadUrl(url: String) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+url).into(this)
        }
    }
}
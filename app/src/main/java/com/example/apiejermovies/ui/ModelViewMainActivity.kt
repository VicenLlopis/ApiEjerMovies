package com.example.apiejermovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiejermovies.data.model.MoviesJson

class ModelViewMainActivity(val movies : List<MoviesJson>) : ViewModel() {




}


@Suppress("UNCHECHED_CAST")
class ModelViewModelFactory(private val movies: List<MoviesJson>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ModelViewMainActivity(movies) as T
    }
}
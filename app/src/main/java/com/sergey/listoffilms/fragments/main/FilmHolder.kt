package com.sergey.listoffilms.fragments.main

import androidx.recyclerview.widget.RecyclerView
import com.sergey.listoffilms.GlideApp
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.databinding.FilmItemLayoutBinding

class FilmHolder(private val filmItem: FilmItemLayoutBinding): RecyclerView.ViewHolder(filmItem.root) {

    fun bind(movie: Movie){
        GlideApp.with(filmItem.poster)
            .load(movie.imgUrl())
            .into(filmItem.poster)
        filmItem.description.text = movie.overview
        filmItem.title.text = movie.title
    }
}
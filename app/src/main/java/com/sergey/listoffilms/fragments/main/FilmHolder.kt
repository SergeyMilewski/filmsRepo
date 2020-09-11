package com.sergey.listoffilms.fragments.main

import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import com.sergey.listoffilms.GlideApp
import com.sergey.listoffilms.R
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.databinding.FilmItemLayoutBinding

class FilmHolder(
    private val filmItem: FilmItemLayoutBinding,
    private val sharedPreferences: SharedPreferences
) : RecyclerView.ViewHolder(filmItem.root) {

    private val cashedList =
        sharedPreferences.getStringSet(FAVORITE_PREF, emptySet())?.toMutableList()

    fun bind(movie: Movie) {
        GlideApp.with(filmItem.poster)
            .load(movie.imgUrl())
            .into(filmItem.poster)
        filmItem.description.text = movie.overview
        filmItem.title.text = movie.title
        filmItem.favoriteButton.setImageResource(if (isFavorite(movie)) R.drawable.ic_favorite else R.drawable.ic_no_favorite)
        filmItem.favoriteButton.setOnClickListener { onFavoriteClick(movie) }
    }

    private fun isFavorite(movie: Movie): Boolean {
        return cashedList!!.contains(movie.id.toString())
    }

    private fun onFavoriteClick(movie: Movie) {
        if (cashedList!!.contains(movie.id.toString())) {
            cashedList.remove(movie.id.toString())
            filmItem.favoriteButton.setImageResource(R.drawable.ic_no_favorite)
        } else {
            cashedList.add(movie.id.toString())
            filmItem.favoriteButton.setImageResource(R.drawable.ic_favorite)
        }
        sharedPreferences.edit().putStringSet(FAVORITE_PREF, cashedList.toSet()).apply()
    }
}
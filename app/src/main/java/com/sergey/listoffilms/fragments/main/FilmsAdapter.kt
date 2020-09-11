package com.sergey.listoffilms.fragments.main

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.databinding.FilmItemLayoutBinding
import javax.inject.Inject

const val FAVORITE_PREF = "Favorite.Pref"

class FilmsAdapter @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PagedListAdapter<Movie, FilmHolder>(COMPARATOR) {

    var clickListener: (Movie?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmHolder(
        FilmItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), sharedPreferences
    )

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onViewAttachedToWindow(holder: FilmHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener { clickListener.invoke(getItem(holder.adapterPosition)) }
    }

    override fun onViewDetachedFromWindow(holder: FilmHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
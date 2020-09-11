package com.sergey.listoffilms.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sergey.listoffilms.api.FilmApi
import com.sergey.listoffilms.api.models.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val filmApi: FilmApi) {

    private var moviesDataSource: MoviesDataSource? = null

    fun getNowPlaying(): LiveData<PagedList<Movie>>{
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()

        val dataSource = object : DataSource.Factory<Int, Movie>() {
            override fun create(): MoviesDataSource {
                return MoviesDataSource(filmApi).also {
                    moviesDataSource = it
                }
            }
        }
        return LivePagedListBuilder(dataSource, config).build()
    }

    fun searchFilms(input: CharSequence) = filmApi.searchMovies(query = input.toString())
}
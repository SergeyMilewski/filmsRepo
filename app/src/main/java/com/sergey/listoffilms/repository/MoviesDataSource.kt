package com.sergey.listoffilms.repository


import androidx.paging.PageKeyedDataSource
import com.sergey.listoffilms.api.FilmApi
import com.sergey.listoffilms.api.models.Movie
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MoviesDataSource @Inject constructor(
    private val filmApi: FilmApi
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        filmApi.getNowPlaying(page = 1)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                callback.onResult(it.body()?.results.orEmpty(), null, 2)
            }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Movie>
    ) {
        filmApi.getNowPlaying(page = params.key)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                callback.onResult(it.body()?.results.orEmpty(), (params.key).inc())
            }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Movie>
    ) {
        filmApi.getNowPlaying(page = params.key)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                callback.onResult(it.body()?.results.orEmpty(), params.key.dec())
            }
    }
}
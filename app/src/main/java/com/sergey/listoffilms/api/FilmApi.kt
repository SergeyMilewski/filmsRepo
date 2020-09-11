package com.sergey.listoffilms.api

import com.sergey.listoffilms.api.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "5dff9c51c4701678954da1387fa74ee1" //TODO  char array would be better for security

interface FilmApi {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Single<Response<MoviesResponse>>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String
    ): Single<Response<MoviesResponse>>
}
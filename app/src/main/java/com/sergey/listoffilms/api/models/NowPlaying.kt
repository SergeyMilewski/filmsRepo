package com.sergey.listoffilms.api.models

import com.google.gson.annotations.SerializedName
import java.util.*

const val IMAGES_URL = "https://image.tmdb.org/t/p/w500"
data class NowPlaying(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: MutableList<Movie>,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,

)

data class Dates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("adult")
    val adult: Boolean
){
    fun imgUrl() = "$IMAGES_URL$posterPath"
}
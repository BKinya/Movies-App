package com.beatrice.moviesapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beatrice.moviesapp.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val originalTitle: String,
    val language: String,
    val releaseDate: String,
    val voteCount: Int,
    val voteAverage: Double,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val overview: String
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    language = language,
    releaseDate = releaseDate,
    voteCount = voteCount,
    voteAverage = voteAverage,
    popularity = popularity,
    posterPath = posterPath,
    backdropPath = backdropPath,
    overview = overview
)

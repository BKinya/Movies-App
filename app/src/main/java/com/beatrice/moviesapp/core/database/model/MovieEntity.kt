package com.beatrice.moviesapp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val originalTitle: String,
    val language: String,
    val releaseDate: String,
    val voteCount: Int,
    var voteAverage: Double,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    var tag: String?
)

fun MovieEntity.toMovie(): MovieDomainModel {
    return MovieDomainModel(
            id = this.id,
            title = this.title,
            originalTitle = this.originalTitle,
            language = this.language,
            releaseDate = this.releaseDate,
            voteCount = this.voteCount,
            voteAverage = this.voteAverage,
            popularity = this.popularity,
            posterPath = this.posterPath,
            backdropPath = this.backdropPath,
            overview = this.overview,
        tag = this.tag

        )
    }

fun List<MovieEntity>.toMoviesList(): List<MovieDomainModel> = this.map {
    MovieDomainModel(
        id = it.id,
        title = it.title,
        originalTitle = it.originalTitle,
        language = it.language,
        releaseDate = it.releaseDate,
        voteCount = it.voteCount,
        voteAverage = it.voteAverage,
        popularity = it.popularity,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        overview = it.overview,
        tag = it.tag
    )
}



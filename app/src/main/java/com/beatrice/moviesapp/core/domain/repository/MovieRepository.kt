package com.beatrice.moviesapp.core.domain.repository

import com.beatrice.moviesapp.core.data.Syncable
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository: Syncable {
    fun getPopularMovies(): Flow<List<MovieDomainModel>>
}
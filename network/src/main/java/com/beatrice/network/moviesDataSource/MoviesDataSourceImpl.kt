package com.beatrice.network.moviesDataSource

import com.beatrice.network.MoviesApiService
import com.beatrice.network.model.MoviesResponse
import com.beatrice.network.util.handleApi
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(private val moviesApiService: MoviesApiService) :
    MoviesDataSource {

    override suspend fun getPopularMovies(): Result<MoviesResponse?> = handleApi {
        moviesApiService.getPopularMovies()
    }
}

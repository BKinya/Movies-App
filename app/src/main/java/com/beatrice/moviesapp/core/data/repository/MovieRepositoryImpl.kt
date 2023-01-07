package com.beatrice.moviesapp.core.data.repository

import com.beatrice.moviesapp.core.data.changeListSync
import com.beatrice.moviesapp.core.data.util.MovieEnums
import com.beatrice.moviesapp.core.database.dao.MovieDao
import com.beatrice.moviesapp.core.database.model.MovieEntity
import com.beatrice.moviesapp.core.database.model.toMoviesList
import com.beatrice.moviesapp.core.network.datasource.MoviesDataSource
import com.beatrice.moviesapp.core.network.model.toMovieEntityList
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel
import com.beatrice.moviesapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val moviesDataSource: MoviesDataSource
) : MovieRepository {
    override fun getPopularMovies(): Flow<List<MovieDomainModel>> = flow {
        val movies = movieDao.getMovies()
        val moviesList = movies.toMoviesList()
        emit(moviesList)
    }

    override suspend fun sync(): Boolean {
        return changeListSync(
            itemsFetcher = {
                val result = moviesDataSource.getPopularMovies()
                result
            },
            changeListFetcher = { movieResult ->
                val fetchedMovies = movieResult?.toMovieEntityList()
                val fetchedMoviesIds = fetchedMovies?.map { it.id }
                val oldMovies = movieDao.getMovies()
                val oldMoviesIds = oldMovies.map { it.id }

                /**
                 * set [NEW] tag on new movies
                 */
                fetchedMovies?.filter {
                    it.id !in oldMoviesIds
                }?.forEach {
                    it.tag = MovieEnums.NEW.name
                }

                /**
                 * Filter old movies that have been updated
                 */
                val moviesToUpdate = fetchedMovies?.filter {
                    it.id in oldMoviesIds
                }

                /**
                 * Filter old movies that have been removed and delete them locally
                 */
                val moviesToDelete =
                    oldMovies.filter { (fetchedMoviesIds != null) && (it.id !in fetchedMoviesIds) }

                val changeListCollection = mapOf(
                    "new" to fetchedMovies?.toList(),
                    "toUpdate" to moviesToUpdate,
                    "toDelete" to moviesToDelete
                )
                changeListCollection
            },
            modelUpdater = { changeList ->
                /**
                 * Delete movies that have been removed
                 */
                val moviesToDelete = changeList["toDelete"]
                moviesToDelete?.let { alist ->
                    val movies = alist as List<MovieEntity>
                    val moviesIds = movies.map { it.id }
                    movieDao.deleteMovies(moviesIds)
                }
                /**
                 * Update existing movies
                 */
                val moviesToUpdate = changeList["toUpdate"]
                moviesToUpdate?.let {
                    val movies = it as List<MovieEntity>
                    movieDao.updateMovies(movies)
                }

                /**
                 * Save new movies
                 */
                val newMovies = changeList["new"]
                newMovies?.let {
                    val movies = it as List<MovieEntity>
                    movieDao.insertMovies(movies)
                }
            },
        )
    }
}
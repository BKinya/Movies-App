package com.beatrice.moviesapp.core.database.dao

import androidx.room.Room
import com.beatrice.moviesapp.core.database.MoviesDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import androidx.test.platform.app.InstrumentationRegistry
import com.beatrice.moviesapp.resources.fakeMovie
import org.junit.Assert
import org.junit.Test

class MovieDaoTest {

    private lateinit var database: MoviesDatabase
    private lateinit var movieDao: MovieDao
    private val moviesIds = listOf(fakeMovie.id)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .allowMainThreadQueries().build()
        movieDao = database.movieDao()
    }

    @After
    fun tearDown() {
        runBlocking {
            movieDao.deleteMovies(moviesIds)
            database.close()
        }
    }

    @Test
    fun insertMovies() {
        runBlocking {
            val moviesTestFake = fakeMovie

            val reposTestListUtil = listOf(moviesTestFake)
            movieDao.insertMovies(reposTestListUtil)

            val result = movieDao.getMovies()
            Assert.assertEquals(reposTestListUtil, result)
        }
    }

    @Test
    fun deleteMovies() {
        runBlocking {
            movieDao.insertMovies(listOf(fakeMovie))
            movieDao.deleteMovies(moviesIds)
            val usersFollowers = movieDao.getMovies()
            assert(usersFollowers.isEmpty())
        }
    }

}
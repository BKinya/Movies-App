package com.beatrice.moviesapp.presentation.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.moviesapp.core.domain.repository.MovieRepository
import com.beatrice.moviesapp.presentation.intent.MovieUiEvent
import com.beatrice.moviesapp.presentation.model.MoviesViewState
import com.beatrice.moviesapp.presentation.util.TimeCapsule
import com.beatrice.moviesapp.presentation.util.TimeTravelCapsule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val MOVIES_STATE_KEY = "movies_state"

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _movieViewState: MutableStateFlow<MoviesViewState> =
        MutableStateFlow(MoviesViewState.Loading)

    val moviesViewState: StateFlow<MoviesViewState> = savedStateHandle.getStateFlow(
        MOVIES_STATE_KEY, MoviesViewState.Loading
    )

    val movieUiEvents = Channel<MovieUiEvent>(Channel.BUFFERED)

    val timeCapsule: TimeCapsule<MoviesViewState> = TimeTravelCapsule { storedState ->
        _movieViewState.tryEmit(storedState)
    }

    init {
        handleIntent()
    }

    fun handleIntent() {
        viewModelScope.launch {
            movieUiEvents.consumeAsFlow().collect { uiEvent ->
                when (uiEvent) {
                    is MovieUiEvent.FetchPopularMovies -> getPopularMovies()
                    is MovieUiEvent.FetchFavouriteMovies -> getFavouriteMovies()
                    is MovieUiEvent.MarkFavorite -> markFavourite()
                }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(ioDispatcher) {
            delay(2000)
            movieRepository.getPopularMovies().collect { moviesList ->
                val newState = MoviesViewState.Data(moviesList)
                savedStateHandle[MOVIES_STATE_KEY] = newState

            }
        }
    }

    private fun getFavouriteMovies() {

    }

    private fun markFavourite() {


    }
}
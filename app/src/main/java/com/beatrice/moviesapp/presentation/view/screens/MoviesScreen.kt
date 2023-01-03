package com.beatrice.moviesapp.presentation.view.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beatrice.moviesapp.presentation.intent.MovieUiEvent
import com.beatrice.moviesapp.presentation.model.MoviesViewState
import com.beatrice.moviesapp.presentation.view.components.ErrorMessageComponent
import com.beatrice.moviesapp.presentation.view.components.MoviesListComponent
import com.beatrice.moviesapp.presentation.view.components.ProgressIndicatorComponent
import com.beatrice.moviesapp.presentation.view.viewmodel.MoviesViewModel
import logcat.logcat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    navigateToMovieDetails: (movieId: Int) -> Unit = {}
) {
    val moviesState = moviesViewModel.moviesViewState.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        moviesViewModel.movieUiEvents.send(MovieUiEvent.FetchPopularMovies)
    }

    Scaffold() { paddingValues ->
        logcat("WHAAAAT"){"IS The state? $moviesState"}
        when (moviesState) {
            is MoviesViewState.Idle -> {
                logcat("WHAAAAT"){"Idle?"}
            }
            is MoviesViewState.Loading -> {
                ProgressIndicatorComponent()
            }
            is MoviesViewState.Data -> {
                logcat("MOVIESsss") { "are ${moviesState.movieDomainModels}" }
                MoviesListComponent(movieDomainModels = moviesState.movieDomainModels, navigateToMovieDetails = navigateToMovieDetails)
            }
            is MoviesViewState.Error -> {
                ErrorMessageComponent(message = moviesState.message)
            }
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MovieScreen()
}

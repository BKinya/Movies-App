package com.beatrice.moviesapp.presentation.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MoviesListComponent(
    movieDomainModels: List<MovieDomainModel>,
    navigateToMovieDetails: (movieId: Int) -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    val showFab by remember {
        derivedStateOf { gridState.firstVisibleItemIndex > 0 }
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFab,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                MoviesFab {
                    coroutineScope.launch {
                        gridState.animateScrollToItem(0)
                    }
                }
            }
        }
    ) { _ ->
        /**
         * 1. Custom grids
         */
        LazyVerticalGrid(
            columns = object : GridCells {
                override fun Density.calculateCrossAxisCellSizes(
                    availableSize: Int,
                    spacing: Int
                ): List<Int> {
                    val firstColumn = (availableSize - spacing) * 2 / 3
                    val secondColumn = availableSize - spacing - firstColumn
                    return listOf(firstColumn, secondColumn)
                }

            },
            state = gridState,
            contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movieDomainModels) { movie ->
                MovieComponent(
                    movieDomainModel = movie,
                    navigateToMovieDetails = navigateToMovieDetails
                )
            }
        }
        /**
         *2.  GridItemSpan
         * maxLinesSpan
         * maxCurrentLineSpan
         */
//        LazyVerticalGrid(
//            columns = GridCells.Adaptive(256.dp),
//            state = gridState,
//            contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp, start = 20.dp, end = 20.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            movies.forEachIndexed { index, movie ->
//                if (index == 0) {
//                    item(
//                        span = { GridItemSpan(maxLineSpan) }
//                    ) {
//                        MovieComponent(movie = movie)
//                    }
//                } else {
//                    item(
//                         span = { GridItemSpan(if (index % 4 == 0) 2 else 1) }
//                    ) {
//                        MovieComponent(movie = movie)
//                    }
//                }
//            }
//        }

    }
}


@Preview(device = Devices.PHONE)
@Composable
fun MoviesListComponentPreviewPhone() {
    Surface(color = Color.White) {
        MoviesListComponent(movieDomainModels = movieDomainModelLists)
    }
}

@Preview(device = Devices.FOLDABLE)
@Composable
fun MoviesListComponentPreviewFoldable() {
    Surface(color = Color.White) {
        MoviesListComponent(movieDomainModels = movieDomainModelLists)
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun MoviesListComponentPreviewTablet() {
    Surface(color = Color.White) {
        MoviesListComponent(movieDomainModels = movieDomainModelLists)
    }
}


val movieDomainModelLists = listOf(
    MovieDomainModel(
        id = 0,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 1,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 2,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 3,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 4,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 5,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 6,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 7,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 8,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
    MovieDomainModel(
        id = 9,
        title = "Abc",
        originalTitle = "ABC",
        voteAverage = 0.0,
        posterPath = "https://image.tmdb.org/t/p/w342/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        backdropPath = "ABC",
        releaseDate = "2022.10.3",
        language = "en",
        popularity = 0.0,
        voteCount = 0,
        overview = "The quick brown fox jumped over a lazy dog." +
                " The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog. " +
                "The quick brown fox jumped over a lazy dog."
    ),
)
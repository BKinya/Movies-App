package com.beatrice.moviesapp.presentation.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beatrice.moviesapp.data.model.Movie
import kotlinx.coroutines.launch
import logcat.logcat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MoviesListComponent(
    movies: List<Movie>,
    resultType: String = "Popular Movies",
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
    ) { paddingValues ->
        LazyVerticalGrid(

            columns = GridCells.Adaptive(256.dp),
            state = gridState,
            contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = resultType,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Serif
                    ),
                    textAlign = TextAlign.Center
                )
            }
            items(movies) { movie ->
                MovieComponent(
                    movie = movie,
                    navigateToMovieDetails = navigateToMovieDetails
                )
            }
        }

//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp, start = 20.dp, end = 20.dp)
//    ) {
//        item {
//            Text(
//                text = resultType,
//                modifier = Modifier.fillMaxWidth(),
//                style = TextStyle(
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.DarkGray,
//                    fontSize = 22.sp,
//                    fontFamily = FontFamily.Serif
//                ),
//                textAlign = TextAlign.Center
//            )
//        }
//        items(movies) { movie ->
//            MovieComponent(
//                movie = movie,
//                navigateToMovieDetails = navigateToMovieDetails
//            )
//        }
//    }
    }
}

@Preview
@Composable
fun MoviesListComponentPreview() {
    Surface(color = Color.White) {
        MoviesListComponent(movies = movieList)
    }
}

val movieList = listOf(
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
    Movie(
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
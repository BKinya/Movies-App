package com.beatrice.moviesapp.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beatrice.moviesapp.R
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel
import com.beatrice.moviesapp.presentation.model.MoviesViewState
import com.beatrice.moviesapp.presentation.view.viewmodel.MoviesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    val movieState = moviesViewModel.moviesViewState.collectAsStateWithLifecycle().value
    val movieDomainModel: MovieDomainModel? = when (movieState) {
        is MoviesViewState.Data -> {
            val test = movieState.movieDomainModels.find { movie -> movie.id == movieId }
            test
        }

        else -> {
            null
        }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w342${movieDomainModel?.posterPath}").build(),
            placeholder = painterResource(id = R.drawable.ic_baseline_movie_24),
            contentDescription = stringResource(R.string.movie_poster),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(27.dp))
        )
        Text(
            text = movieDomainModel?.title ?: "",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                color = Color.DarkGray
            )
        )
        Text(
            text = "Original title: ${movieDomainModel?.originalTitle}",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        )
        Text(
            text = movieDomainModel?.overview ?: "",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    Surface(color = Color.White) {
        MovieDetailScreen(movieId = 0)
    }
}

val movieDomainModel = MovieDomainModel(
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
)
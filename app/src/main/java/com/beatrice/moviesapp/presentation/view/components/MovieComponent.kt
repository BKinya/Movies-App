package com.beatrice.moviesapp.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beatrice.moviesapp.R
import com.beatrice.moviesapp.core.domain.model.MovieDomainModel

@Composable
fun MovieComponent(
    movieDomainModel: MovieDomainModel,
    navigateToMovieDetails: (movieId: Int) -> Unit = {}
) {
    /**
     * TODO: Alternating colors background and the border
     */
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(250.dp)
            .selectable(
                selected = true,
                onClick = {
                    navigateToMovieDetails(movieDomainModel.id)
                }),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)


    ) {
        Row(
            modifier = Modifier.padding(7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w342${movieDomainModel.posterPath}").build(),
                placeholder = painterResource(id = R.drawable.ic_baseline_movie_24),
                contentDescription = stringResource(R.string.movie_poster),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(27.dp))
            )
            ConstraintLayout {
                val (titleText, dateText, divider1, langText, overviewText) = createRefs()
                Text(
                    text = movieDomainModel.title,
                    modifier = Modifier.constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = movieDomainModel.releaseDate,
                    modifier = Modifier.constrainAs(dateText) {
                        top.linkTo(titleText.bottom, 10.dp)
                        start.linkTo(parent.start)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .constrainAs(divider1) {
                            top.linkTo(titleText.bottom, 10.dp)
                            start.linkTo(dateText.end, 4.dp)
                            bottom.linkTo(overviewText.top)
                        }
                        .height(12.dp)
                        .width(1.dp)
                )
                Text(
                    text = movieDomainModel.language,
                    modifier = Modifier
                        .constrainAs(langText) {
                            top.linkTo(titleText.bottom, 10.dp)
                            start.linkTo(divider1.end, 4.dp)
                        },
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
                Text(
                    text = "${movieDomainModel.overview.take(70)} ...",
                    modifier = Modifier
                        .constrainAs(overviewText) {
                            top.linkTo(dateText.bottom, 10.dp)
                            start.linkTo(parent.start)
                        },
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                )
            }
        }


    }
}

@Preview
@Composable
fun MovieComponentPreview() {
    Surface(color = Color.White) {
        MovieComponent(
            movieDomainModel = MovieDomainModel(
                id = 0,
                title = "Abc",
                originalTitle = "ABC",
                voteAverage = 0.0,
                posterPath = "ABC",
                backdropPath = "ABC",
                releaseDate = "2022.10.3",
                language = "en",
                popularity = 0.0,
                voteCount = 0,
                overview = "The quick brown fox jumped over a lazy dog." +
                        " The quick brown fox jumped over a lazy dog. " +
                        "The quick brown fox jumped over a lazy dog. " +
                        "The quick brown fox jumped over a lazy dog.",
                tag = null
            )
        )

    }
}
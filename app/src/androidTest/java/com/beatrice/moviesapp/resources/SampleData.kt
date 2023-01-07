package com.beatrice.moviesapp.resources

import com.beatrice.moviesapp.core.database.model.MovieEntity

val fakeMovie = MovieEntity(
    id = 123,
    title = "Prison Break",
    originalTitle = "The Prison Break",
    language = "en",
    releaseDate = "December",
    voteCount = 12367,
    voteAverage = 12.5,
    popularity = 123.1,
    posterPath = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F6%2F2016%2F01%2Fprison-break.jpg&q=60",
    backdropPath = "https://m.media-amazon.com/images/M/MV5BNDFmMzM3NzctZGE0MC00NGVmLTljZmUtYTNiZjA2ZTE4NjQwXkEyXkFqcGdeQXVyNzg3MDUyMzc@._V1_.jpg",
    overview = "Great series",
    tag = null
)
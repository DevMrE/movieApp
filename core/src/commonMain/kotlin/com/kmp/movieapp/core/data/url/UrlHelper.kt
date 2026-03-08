package com.kmp.movieapp.core.data.url

object UrlHelper {

    const val BASE_URL = "api.themoviedb.org"
    const val API_VERSION_PATH = "/3"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val BEARER_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NmIzZmVmODdiN2E1YWM4ZDJjMzA4ZDAzMjNiZDI0MSIsIm5iZiI6MTcxMzk4NjcxMS44MzIsInN1YiI6IjY2Mjk1Yzk3OTFmMGVhMDE2NTAwZjA0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AFrViCEd8O5BLkcUkWC3zLORpiMFyo0Zq8y6fuuTWYE"

    const val MOVIE_ENDPOINT = "/movie"
    const val MOVIE_GENRE_ENDPOINT = "/genre$MOVIE_ENDPOINT/list"

    const val MOVIE_DISCOVER_ENDPOINT = "/discover"

    const val SERIES_ENDPOINT = "/tv"
}

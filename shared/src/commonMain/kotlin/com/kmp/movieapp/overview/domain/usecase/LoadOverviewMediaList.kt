package com.kmp.movieapp.overview.domain.usecase

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.overview.domain.model.OverViewMedia
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

internal class LoadOverviewMediaList(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
) {

    suspend operator fun invoke(
        mediaCategory: MediaCategory,
        page: Int = 1
    ): Flow<List<OverViewMedia>> = when (mediaCategory) {
        MediaCategory.MOVIE -> movieRepository.getPopularMovies(page).map { movieList ->
            movieList.map { movie ->
                OverViewMedia(
                    id = movie.id,
                    title = movie.movieInfo.title,
                    posterPath = movie.movieImage.posterPath,
                    backdropPath = movie.movieImage.backdropPath,
                    type = movie.type
                )
            }
        }

        MediaCategory.SERIES -> seriesRepository.getPopularSeries(page).map { seriesList ->
            seriesList.map { series ->
                OverViewMedia(
                    id = series.id,
                    title = series.name,
                    posterPath = series.posterPath,
                    backdropPath = series.backdropPath,
                    type = series.type
                )
            }
        }

        else -> {
            logI("loading next series, What the...")

            return emptyFlow()
        }
    }
}
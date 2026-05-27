package com.kmp.movieapp.overview_list.domain.usecase

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.movie.domain.usecase.GetPopularMoviesUseCase
import com.kmp.movieapp.overview_list.domain.model.OverViewMedia
import com.kmp.movieapp.series.domain.usecase.GetPopularSeriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class LoadMediaListForCategoryUseCase(
    private val popularSeries: GetPopularSeriesUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        mediaCategory: MediaCategory,
        page: Int = 1
    ): Flow<List<OverViewMedia>> {
        val data = when (mediaCategory) {
            MediaCategory.MOVIE -> popularMoviesUseCase(page).map { movieList ->
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

            MediaCategory.SERIES -> popularSeries(page).map { seriesList ->
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

                flowOf(emptyList())
            }
        }

        return data
    }
}
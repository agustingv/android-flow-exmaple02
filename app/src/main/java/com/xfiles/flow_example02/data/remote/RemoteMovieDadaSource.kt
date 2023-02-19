package com.xfiles.flow_example02.data.remote

import com.xfiles.flow_example02.data.model.MovieList
import com.xfiles.flow_example02.application.AppConstants
import com.xfiles.flow_example02.repository.WebService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteMovieDadaSource(private val webService: WebService) {

    val getMovieList : Flow<MovieList> = flow {
        while (true) {
            val movies =
                webService.getMovieList(lang = AppConstants.LANG, apiKey = AppConstants.API_KEY)
            emit(movies)
            delay(5000)
        }
    }
}
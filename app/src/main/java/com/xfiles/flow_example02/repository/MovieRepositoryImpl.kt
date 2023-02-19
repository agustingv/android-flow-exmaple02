package com.xfiles.flow_example02.repository

import com.xfiles.example01.repository.MovieRepository
import com.xfiles.flow_example02.data.model.MovieList
import com.xfiles.flow_example02.data.remote.RemoteMovieDadaSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDadaSource): MovieRepository
{
    override suspend fun getMovieList(): Flow<MovieList> {
        return dataSourceRemote.getMovieList.onEach {
            it.results.map { movie ->
                movie.title = "Modificando el flow: "+movie.title
            }
        }
    }
}

package com.xfiles.example01.repository

import com.xfiles.flow_example02.data.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(): Flow<MovieList>
}
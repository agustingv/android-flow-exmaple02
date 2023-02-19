package com.xfiles.flow_example02.data.model

data class Movie(
    var id: Int = -1,
    var title: String = "",
    var poster_path: String = ""
)

data class MovieList(val results: List<Movie> = listOf())

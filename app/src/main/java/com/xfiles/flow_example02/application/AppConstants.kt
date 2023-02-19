package com.xfiles.flow_example02.application

import java.io.File

object AppConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "YOUR_API_KEY"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val  CACHE_SIZE = 10 * 1024 * 1024
    const val LANG = "es_ES"
    var CACHE_DIR: File = File("")
}

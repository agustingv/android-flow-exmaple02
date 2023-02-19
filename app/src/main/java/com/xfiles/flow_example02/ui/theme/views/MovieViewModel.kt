package com.xfiles.flow_example02.ui.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xfiles.example01.repository.MovieRepository
import com.xfiles.flow_example02.data.model.Movie
import com.xfiles.flow_example02.data.model.MovieList
import com.xfiles.flow_example02.data.remote.RemoteMovieDadaSource
import com.xfiles.flow_example02.repository.MovieRepositoryImpl
import com.xfiles.flow_example02.repository.RetrofitClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    private val _state = MutableStateFlow(MovieViewModelUiState())
    val state: StateFlow<MovieViewModelUiState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = MovieViewModelUiState(
                loading = true,
            )
            try {
                val popularFlow = repo.getMovieList()
                popularFlow.collect { popular ->
                    _state.value = MovieViewModelUiState(
                        loading = false,
                        popular = popular,

                        )
                }
            } catch (e: Exception) {
                Log.d("Main view Model", "${e.message}")
            }
        }
    }
}

data class MovieViewModelUiState(
    val loading: Boolean = true,
    val popular: MovieList = MovieList(),
)

val repo = MovieRepositoryImpl(
    RemoteMovieDadaSource(RetrofitClient.webService)
)

fun viewModelMainScreenProviderFactoryOf(
): ViewModelProvider.Factory = MovieViewMainScreenFactory(repo)

class MovieViewMainScreenFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
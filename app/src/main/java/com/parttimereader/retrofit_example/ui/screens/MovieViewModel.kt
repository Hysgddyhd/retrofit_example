package com.parttimereader.retrofit_example.ui.screens


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parttimereader.retrofit_example.network.MovieApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MovieUiState {
    data class Success(val photos: String) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}


class MovieViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMoviePhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    private fun getMoviePhotos() {
        viewModelScope.launch {
             try {
                val listResult = MovieApi.retrofitService.getPopularMovies()
                movieUiState = MovieUiState.Success(
                    "Success: ${listResult.size} Movie photos retrieved"
                )

            } catch (e: IOException) {
                 MovieUiState.Error
        }

        }
    }
}

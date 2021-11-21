package ml.zedlabs.tvtracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.GetMovieDetailUseCase
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    val topRatedMovieListLiveData = mutableStateOf<Resource<MovieListResponse>>(Uninitialised())
    val similarMovieListLiveData = mutableStateOf<Resource<MovieListResponse>>(Uninitialised())
    val movieDetailLiveData = mutableStateOf<Resource<MovieDetailResponse>>(Uninitialised())

    fun getMovieList(listType: String, page: Int) {
        topRatedMovieListLiveData.value = Loading()
        viewModelScope.launch {
            topRatedMovieListLiveData.value =
                getMovieListUseCase.getMovieList(listType = listType, page = page)
        }
    }

    fun getSimilarMovieList(movieId: Int, page: Int) {
        similarMovieListLiveData.value = Loading()
        viewModelScope.launch {
            similarMovieListLiveData.value =
                getMovieListUseCase.getSimilarMovies(movieId = movieId, page = page)
        }
    }

    fun getMovieDetail(movieId: Int) {
        movieDetailLiveData.value = Loading()
        viewModelScope.launch {
            movieDetailLiveData.value =
                getMovieDetailUseCase.getMovieDetails(movieId = movieId)
        }
    }

}
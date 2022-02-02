package ml.zedlabs.tvtracker.ui.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.data.util.Constants
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.GetMovieDetailUseCase
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    val topRatedMovieListState = mutableStateOf<Resource<MovieListResponse>>(Uninitialised())
    val similarMovieListState = mutableStateOf<Resource<MovieListResponse>>(Uninitialised())
    val movieDetailState = mutableStateOf<Resource<MovieDetailResponse>>(Uninitialised())
    val movieUserReviewState = mutableStateOf<Resource<UserReviewResponse>>(Uninitialised())
    val recommendedMovieListState = mutableStateOf<Resource<MovieListResponse>>(Uninitialised())

    init {
        getMovieList(Constants.top_rated, 1)
    }

    //have different methods for different list types
    fun getMovieList(listType: String, page: Int){
        topRatedMovieListState.value = Loading()
        viewModelScope.launch {
            topRatedMovieListState.value =
                getMovieListUseCase.getMovieList(listType = listType, page = page)
        }
    }

    fun getSimilarMovieList(movieId: Int, page: Int) {
        similarMovieListState.value = Loading()
        viewModelScope.launch {
            similarMovieListState.value =
                getMovieListUseCase.getSimilarMovies(movieId = movieId, page = page)
        }
    }

    fun getMovieDetail(movieId: Int) {
        movieDetailState.value = Loading()
        viewModelScope.launch {
            movieDetailState.value =
                getMovieDetailUseCase.getMovieDetails(movieId = movieId)
        }
    }

    fun getUserMovieReviews(movieId: Int, page: Int) {
        movieUserReviewState.value = Loading()
        viewModelScope.launch {
            movieUserReviewState.value =
                    getMovieDetailUseCase.getUserMovieReview(movieId = movieId, page = page)
        }
    }

    fun getRecommendedMovies(movieId: Int, page: Int) {
        recommendedMovieListState.value = Loading()
        viewModelScope.launch {
            recommendedMovieListState.value =
                getMovieListUseCase.getRecommendedMovies(movieId = movieId, page = page)
        }
    }

}
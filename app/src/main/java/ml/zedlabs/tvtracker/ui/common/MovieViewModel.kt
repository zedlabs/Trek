package ml.zedlabs.tvtracker.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.Loading
import ml.zedlabs.domain.model.Resource.Uninitialised
import ml.zedlabs.domain.model.common.MediaSortingParam
import ml.zedlabs.domain.model.common.MediaSortingParam.*
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.GetMovieDetailUseCase
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import ml.zedlabs.tvtracker.util.Constants.MIN_PAGE
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    private val _topRatedMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val topRatedMovieListState = _topRatedMovieListState.asStateFlow()

    private val _upcomingMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val upcomingMovieListState = _upcomingMovieListState.asStateFlow()

    private val _popularMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val popularMovieListState = _popularMovieListState.asStateFlow()

    private val _nowPlayingMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val nowPlayingMovieListState = _nowPlayingMovieListState.asStateFlow()

    private val _onTheAirMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val onTheAirMovieListState = _onTheAirMovieListState.asStateFlow()

    private val _similarMovieListState =
        MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val similarMovieListState = _similarMovieListState.asStateFlow()

    val movieDetailState = MutableStateFlow<Resource<MovieDetailResponse>>(Uninitialised())
    val movieUserReviewState = MutableStateFlow<Resource<UserReviewResponse>>(Uninitialised())
    val recommendedMovieListState = MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())

    init {
        for(param in MediaSortingParam.values()) {
            getMovieList(param, MIN_PAGE)
        }
    }

    // we update different state flow based on the selected
    // @link{MediaSortingParam}, as all the flows are collected
    // on the same screen.
    fun getMovieList(listType: MediaSortingParam, page: Int) {
        when (listType) {
            TOP_RATED -> {
                _topRatedMovieListState.value = Loading()
                viewModelScope.launch {
                    _topRatedMovieListState.value =
                        getMovieListUseCase.getMovieList(listType = listType.query, page = page)
                }
            }
            UPCOMING -> {
                _upcomingMovieListState.value = Loading()
                viewModelScope.launch {
                    _upcomingMovieListState.value =
                        getMovieListUseCase.getMovieList(listType = listType.query, page = page)
                }
            }
            POPULAR -> {
                _popularMovieListState.value = Loading()
                viewModelScope.launch {
                    _popularMovieListState.value =
                        getMovieListUseCase.getMovieList(listType = listType.query, page = page)
                }
            }
            NOW_PLAYING -> {
                _nowPlayingMovieListState.value = Loading()
                viewModelScope.launch {
                    _nowPlayingMovieListState.value =
                        getMovieListUseCase.getMovieList(listType = listType.query, page = page)
                }
            }
        }
    }

    fun getSimilarMovieList(movieId: Int, page: Int) {
        _similarMovieListState.value = Loading()
        viewModelScope.launch {
            _similarMovieListState.value =
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
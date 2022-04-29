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
import ml.zedlabs.domain.model.common.MovieSortingParam
import ml.zedlabs.domain.model.common.MovieSortingParam.*
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.GetMovieDetailUseCase
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import ml.zedlabs.domain.usecases.GetWebScrapingDataUseCase
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
    val getWebScrapingDataUseCase: GetWebScrapingDataUseCase,
) : ViewModel() {

    private val _imdbRatingState =
        MutableStateFlow<Resource<Double>>(Resource.Uninitialised())
    val imdbRatingState = _imdbRatingState.asStateFlow()

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

    private val _movieDetailState =
        MutableStateFlow<Resource<MovieDetailResponse>>(Uninitialised())
    val movieDetailState = _movieDetailState.asStateFlow()

    private val _movieUserReviewState = MutableStateFlow<Resource<UserReviewResponse>>(Uninitialised())
    val movieUserReviewState = _movieUserReviewState.asStateFlow()

    private val _recommendedMovieListState = MutableStateFlow<Resource<MovieListResponse>>(Uninitialised())
    val recommendedMovieListState = _recommendedMovieListState.asStateFlow()

    fun getImdbRating(imdbId: String) {
        viewModelScope.launch {
            _imdbRatingState.value = getWebScrapingDataUseCase.getImdbRating(imdbId = imdbId)
        }
    }

    // we update different state flow based on the selected
    // @link{MediaSortingParam}, as all the flows are collected
    // on the same screen.
    fun getMovieList(listType: MovieSortingParam, page: Int) {
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
        _movieDetailState.value = Loading()
        viewModelScope.launch {
            _movieDetailState.value =
                getMovieDetailUseCase.getMovieDetails(movieId = movieId)
        }
    }

    fun getUserMovieReviews(movieId: Int, page: Int) {
        _movieUserReviewState.value = Loading()
        viewModelScope.launch {
            _movieUserReviewState.value =
                getMovieDetailUseCase.getUserMovieReview(movieId = movieId, page = page)
        }
    }

    fun getRecommendedMovies(movieId: Int, page: Int) {
        _recommendedMovieListState.value = Loading()
        viewModelScope.launch {
            _recommendedMovieListState.value =
                getMovieListUseCase.getRecommendedMovies(movieId = movieId, page = page)
        }
    }

}
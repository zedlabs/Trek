package ml.zedlabs.tvtracker.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
import ml.zedlabs.domain.model.common.MovieSortingParam
import ml.zedlabs.domain.model.common.TvSortingParam
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.model.tv.TvSeasonDetails
import ml.zedlabs.domain.usecases.GetTvDetailUseCase
import ml.zedlabs.domain.usecases.GetTvListUseCase
import ml.zedlabs.domain.usecases.GetTvSeasonDetailUseCase
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    val getTvDetailsUseCase: GetTvDetailUseCase,
    val getTvSeasonDetailUseCase: GetTvSeasonDetailUseCase,
    val getTvListUseCase: GetTvListUseCase,
) : ViewModel() {

    private val _topRatedTvListState = MutableStateFlow<Resource<TvListResponse>>(Uninitialised())
    val topRatedTvListState = _topRatedTvListState.asStateFlow()

    private val _onTheAirTvListState = MutableStateFlow<Resource<TvListResponse>>(Uninitialised())
    val onTheAirTvListState = _onTheAirTvListState.asStateFlow()

    private val _popularTvListState = MutableStateFlow<Resource<TvListResponse>>(Uninitialised())
    val popularTvListState = _popularTvListState.asStateFlow()

    private val _similarTvListState = MutableStateFlow<Resource<TvListResponse>>(Uninitialised())
    val similarTvListState = _similarTvListState.asStateFlow()

    private val _tvDetailState = MutableStateFlow<Resource<TvDetailResponse>>(Uninitialised())
    val tvDetailState = _tvDetailState.asStateFlow()

    private val _tvUserReviewState = MutableStateFlow<Resource<UserReviewResponse>>(Uninitialised())
    val tvUserReviewState = _tvUserReviewState.asStateFlow()

    private val _tvSeasonDetailsState = MutableStateFlow<Resource<TvSeasonDetails>>(Uninitialised())
    val tvSeasonDetailsState = _tvSeasonDetailsState.asStateFlow()

    // we update different state flow based on the selected
    // @link{MediaSortingParam}, as all the flows are collected
    // on the same screen.
    fun getTvList(listType: TvSortingParam, page: Int) {
        when (listType) {
            TvSortingParam.TOP_RATED -> {
                _topRatedTvListState.value = Loading()
                viewModelScope.launch {
                    _topRatedTvListState.value =
                        getTvListUseCase.getTvShowList(listType = listType.query, page = page)
                }
            }
            TvSortingParam.ON_THE_AIR -> {
                _onTheAirTvListState.value = Loading()
                viewModelScope.launch {
                    _onTheAirTvListState.value =
                        getTvListUseCase.getTvShowList(listType = listType.query, page = page)
                }
            }
            TvSortingParam.POPULAR -> {
                _popularTvListState.value = Loading()
                viewModelScope.launch {
                    _popularTvListState.value =
                        getTvListUseCase.getTvShowList(listType = listType.query, page = page)
                }
            }
        }
    }


    fun getSimilarTvList(tvId: Int, page: Int) {
        _similarTvListState.value = Loading()
        viewModelScope.launch {
            _similarTvListState.value = getTvListUseCase.getSimilarShows(tvId, page)
        }
    }

    fun getTvShowDetails(tvId: Int) {
        _tvDetailState.value = Loading()
        viewModelScope.launch {
            _tvDetailState.value = getTvDetailsUseCase.getTvShowDetails(tvId)
        }
    }

    fun getTvUserReviews(tvId: Int, page: Int) {
        _tvUserReviewState.value = Loading()
        viewModelScope.launch {
            _tvUserReviewState.value = getTvDetailsUseCase.getTvShowReviews(tvId, page)
        }
    }

    fun getTvSeasonDetails(tvId: Int, seasonNumber: Int) {
        _tvSeasonDetailsState.value = Loading()
        viewModelScope.launch {
            _tvSeasonDetailsState.value =
                getTvSeasonDetailUseCase.getTvSeasonDetails(tvId, seasonNumber)
        }
    }

}
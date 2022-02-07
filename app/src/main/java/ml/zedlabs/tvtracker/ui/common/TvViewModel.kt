package ml.zedlabs.tvtracker.ui.common

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.data.util.Constants
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
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
): ViewModel() {

    val topRatedTvListState = mutableStateOf<Resource<TvListResponse>>(Uninitialised())
    val similarTvListState = mutableStateOf<Resource<TvListResponse>>(Uninitialised())
    val tvDetailState = mutableStateOf<Resource<TvDetailResponse>>(Uninitialised())
    val tvUserReviewState = mutableStateOf<Resource<UserReviewResponse>>(Uninitialised())
    val tvSeasonDetailsState = mutableStateOf<Resource<TvSeasonDetails>>(Uninitialised())

    fun getTopRatedTvList(page: Int) {
        topRatedTvListState.value = Loading()
        viewModelScope.launch {
            //topRatedTvListState.value = getTvListUseCase.getTvShowList(Constants.top_rated, page)
        }
    }

    fun getSimilarTvList(tvId: Int, page: Int) {
        similarTvListState.value = Loading()
        viewModelScope.launch {
            similarTvListState.value = getTvListUseCase.getSimilarShows(tvId, page)
        }
    }

    fun getTvShowDetails(tvId: Int) {
        tvDetailState.value = Loading()
        viewModelScope.launch {
            tvDetailState.value = getTvDetailsUseCase.getTvShowDetails(tvId)
        }
    }

    fun getTvUserReviews(tvId: Int, page: Int) {
        tvUserReviewState.value = Loading()
        viewModelScope.launch {
            tvUserReviewState.value = getTvDetailsUseCase.getTvShowReviews(tvId, page)
        }
    }

    fun getTvSeasonDetails(tvId: Int, seasonNumber: Int) {
        tvSeasonDetailsState.value = Loading()
        viewModelScope.launch {
            tvSeasonDetailsState.value = getTvSeasonDetailUseCase.getTvSeasonDetails(tvId, seasonNumber)
        }
    }

}
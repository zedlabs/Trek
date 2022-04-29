package ml.zedlabs.tvtracker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.usecases.GetWebScrapingDataUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val webScrapingDataUseCase: GetWebScrapingDataUseCase
) : ViewModel() {

    private val _imdbRatingState =
        MutableStateFlow<Resource<Double>>(Resource.Uninitialised())
    val imdbRatingState = _imdbRatingState.asStateFlow()

    fun getImdbRating(imdbId: String) {
        viewModelScope.launch {
            _imdbRatingState.value = webScrapingDataUseCase.getImdbRating(imdbId = imdbId)
        }
    }
}
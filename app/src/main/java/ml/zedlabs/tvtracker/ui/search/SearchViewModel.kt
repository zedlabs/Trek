package ml.zedlabs.tvtracker.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.SearchUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchListState =
        MutableStateFlow<Resource<MovieListResponse>>(Resource.Uninitialised())
    val searchListState = _searchListState.asStateFlow()

    fun searchData(query: String, page: Int) {
        _searchListState.value = Resource.Loading()
        viewModelScope.launch {
            _searchListState.value =
                searchUseCase.search(query = query, page = page)
        }
    }
}
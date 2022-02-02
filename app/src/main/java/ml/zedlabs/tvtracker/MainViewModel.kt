package ml.zedlabs.tvtracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {




}
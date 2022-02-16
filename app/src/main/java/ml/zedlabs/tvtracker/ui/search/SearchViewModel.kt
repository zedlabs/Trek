package ml.zedlabs.tvtracker.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ml.zedlabs.domain.model.common.SearchListItem

import ml.zedlabs.domain.usecases.SearchUseCase
import ml.zedlabs.tvtracker.util.Constants
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchUseCase: SearchUseCase
) : ViewModel() {

    fun getMovies(query: String): Flow<PagingData<SearchListItem>> {
        return Pager(PagingConfig(pageSize = Constants.SEARCH_PAGE_SIZE)) {
            SearchPagingSource(
                searchUseCase,
                query,
            )
        }.flow
    }
}
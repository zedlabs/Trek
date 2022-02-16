package ml.zedlabs.tvtracker.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.common.SearchListItem
import ml.zedlabs.domain.usecases.SearchUseCase
import java.lang.Exception

/**
 * putting this in the UI layer
 * doesn't make sense elsewhere
 */
class SearchPagingSource(
    private val searchUseCase: SearchUseCase,
    private val query: String
) : PagingSource<Int, SearchListItem>() {
    override fun getRefreshKey(state: PagingState<Int, SearchListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchListItem> {
        return try {
            val nextPage = params.key ?: 1
            val searchListResponse = searchUseCase.search(query, nextPage)
            LoadResult.Page(
                data = searchListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = searchListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}
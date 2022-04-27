package ml.zedlabs.tvtracker.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.common.MediaType
import ml.zedlabs.domain.model.common.SearchListItem
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.tvtracker.R
import ml.zedlabs.tvtracker.util.Constants
import ml.zedlabs.tvtracker.util.appendAsImageUrl
import java.util.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    val searchViewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchScreenParentLayout()
            }
        }
    }

    /**
     * search query is a mutable state variable and when it is updated
     * by the user recomposition happens and the searchList is updated
     */
    @Composable
    fun SearchScreenParentLayout() {
        val searchQuery = rememberSaveable { mutableStateOf("") }
        val searchList: LazyPagingItems<SearchListItem> =
            searchViewModel.getMovies(searchQuery.value).collectAsLazyPagingItems()

        Scaffold(
            topBar = {
                Row {
                    TextField(
                        value = searchQuery.value,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {
                            searchQuery.value = it
                        },
                        placeholder = {
                            Text(text = "Search For Movies, TV Shows, Anime")
                        }
                    )
                }
            }
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(
                    items = searchList,
                ) { item ->
                    SearchItem(item)
                }
            }
        }
    }

    @Composable
    fun SearchItem(
        item: SearchListItem?
    ) {
        item ?: return
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
                .clickable {
                    onItemClick(
                        item.id,
                        when (item.media_type) {
                            MediaType.TV.name.lowercase(Locale.getDefault()) -> MediaType.TV
                            else -> MediaType.MOVIE
                        }
                    )
                }
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(item.poster_path?.appendAsImageUrl()),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(
                    color = Color.White,
                    text = item.title ?: "",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(color = Color.Black.copy(alpha = 0.4f))
                        .padding(10.dp)
                        .fillMaxWidth()
                )

            }
        }
    }

    private fun onItemClick(mediaId: Int, mediaType: MediaType) {
        val bundle = bundleOf("mediaId" to mediaId)
        with(view?.findNavController()) {
            // exit out if the nav controller instance cannot be found
            this?: return
            when(mediaType) {
                MediaType.MOVIE -> navigate(R.id.search_to_movie_det, bundle)
                MediaType.TV -> navigate(R.id.search_to_tv_det, bundle)

            }
        }
    }
}
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.common.SearchListItem
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.tvtracker.util.Constants
import ml.zedlabs.tvtracker.util.appendAsImageUrl

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

    @Composable
    fun SearchScreenParentLayout() {
        //val scrollState = rememberScrollState()
        val searchList: LazyPagingItems<SearchListItem> =
            searchViewModel.getMovies("wes anderson").collectAsLazyPagingItems()

        LazyColumn() {
            items(
                items = searchList,
            ) { item ->
                HomeScreenListItem(
                    title = item?.title ?: "",
                    imageUrl = item?.poster_path ?: "",
                    mediaId = item?.id ?: 0,
                )
            }
        }
    }

    @Composable
    fun HomeScreenListItem(
        title: String,
        imageUrl: String,
        mediaId: Int
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
//                .clickable {
//                    itemClick(mediaId)
//                }
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(imageUrl.appendAsImageUrl()),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(
                    color = Color.White,
                    text = title,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(color = Color.Black.copy(alpha = 0.4f))
                        .padding(10.dp)
                        .fillMaxWidth()
                )

            }
        }
    }
}
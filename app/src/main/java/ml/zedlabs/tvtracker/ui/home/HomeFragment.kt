package ml.zedlabs.tvtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.MediaSortingParam
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.movie.MovieResult
import ml.zedlabs.tvtracker.R
import ml.zedlabs.tvtracker.ui.common.MovieViewModel
import ml.zedlabs.tvtracker.util.Constants
import ml.zedlabs.tvtracker.util.appendAsImageUrl

/**
 *
 * @author {@zedlabs}
 * @created_on {5-02-2022}
 *
 * This is the home screen for the app, default selection
 * in the bottom navigation layout
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreenParentLayout()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(param in MediaSortingParam.values()) {
            movieViewModel.getMovieList(param, Constants.MIN_PAGE)
        }
    }
    /**
     * Main screen composable which is the container for all the horizontally
     * scrolling lists, itself wrapped in a vertical scroll state listener
     */
    @Composable
    fun HomeScreenParentLayout() {
        val scrollState = rememberScrollState()
        val topRatedList by movieViewModel.topRatedMovieListState.collectAsState()
        val upcomingList by movieViewModel.upcomingMovieListState.collectAsState()
        val popularList by movieViewModel.popularMovieListState.collectAsState()
        val nowPlayingList by movieViewModel.nowPlayingMovieListState.collectAsState()

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            ListResponseWrapper("\uD83D\uDCC8 Top Rated", topRatedList)
            ListResponseWrapper("\uD83D\uDCE6 Upcoming", upcomingList)
            ListResponseWrapper("\uD83D\uDE80 Popular", popularList)
            ListResponseWrapper("\uD83C\uDFA5 Now Playing", nowPlayingList)
            Spacer(modifier = Modifier.size(200.dp))
        }
    }

    /**
     * Wrapper function for all the lists, determines the state of the list
     * based on the wrapping @link{Resource}.
     */
    @Composable
    fun ListResponseWrapper(title: String, response: Resource<MovieListResponse>) {

        // when expression is non-exhaustive because, we don't need to handle
        // uninitialised state of the StateFlow(collected as compose state)
        when (response) {
            is Resource.Error -> {
                // stop shimmer
            }
            is Resource.Loading -> {
                // start shimmer
            }
            is Resource.Success -> {
                HomeScreenItemsRow(title, items = response.data?.results ?: listOf())
            }
        }
    }

    @Composable
    fun HomeScreenItemsRow(title: String, items: List<MovieResult>) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(20.dp),
                fontSize = 20.sp,
            )
            LazyRow {
                itemsIndexed(items = items) { _, item ->
                    HomeScreenListItem(
                        item.title,
                        item.poster_path,
                        item.id,
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreenListItem(title: String, imageUrl: String, mediaId: Int) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
                .clickable {
                    onItemClick(mediaId)
                }
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

    private fun onItemClick(mediaId: Int) {
        val bundle = bundleOf("mediaId" to mediaId)
        view?.findNavController()?.navigate(R.id.home_to_detail, bundle)
    }
}



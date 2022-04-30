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
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.MovieSortingParam
import ml.zedlabs.domain.model.common.TvSortingParam
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.movie.MovieResult
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.model.tv.TvResult
import ml.zedlabs.tvtracker.R
import ml.zedlabs.tvtracker.base.BaseAndroidFragment
import ml.zedlabs.tvtracker.ui.common.MovieViewModel
import ml.zedlabs.tvtracker.ui.common.TvViewModel
import ml.zedlabs.tvtracker.ui.theme.TvTrackerTheme
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
class HomeFragment : BaseAndroidFragment() {

    private val movieViewModel: MovieViewModel by activityViewModels()
    private val tvViewModel: TvViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (param in MovieSortingParam.values()) {
            movieViewModel.getMovieList(param, Constants.MIN_PAGE)
        }
        for (param in TvSortingParam.values()) {
            tvViewModel.getTvList(param, Constants.MIN_PAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TvTrackerTheme(
                    darkTheme = readBooleanFromSharedPreference(Constants.IS_DARK_THEME_ENABLED)
                ) {
                    HomeScreenParentLayout()
                }
            }
        }
    }

    /**
     * Main screen composable which is the container for all the horizontally
     * scrolling lists, itself wrapped in a vertical scroll state listener
     */
    @Composable
    fun HomeScreenParentLayout() {
        val scrollState = rememberScrollState()
        //movie list data
        val topRatedMovieList by movieViewModel.topRatedMovieListState.collectAsState()
        val upcomingMovieList by movieViewModel.upcomingMovieListState.collectAsState()
        val popularMovieList by movieViewModel.popularMovieListState.collectAsState()
        val nowPlayingMovieList by movieViewModel.nowPlayingMovieListState.collectAsState()

        //tv list data
        val topRatedTvList by tvViewModel.topRatedTvListState.collectAsState()
        val upcomingTvList by tvViewModel.onTheAirTvListState.collectAsState()
        val popularTvList by tvViewModel.popularTvListState.collectAsState()

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            ListResponseWrapper("\uD83D\uDCC8 Top Rated", topRatedMovieList)
            ListResponseWrapper("\uD83D\uDCE6 Upcoming", upcomingMovieList)
            ListResponseWrapper("\uD83D\uDE80 Popular", popularMovieList)
            ListResponseWrapper("\uD83C\uDFA5 Now Playing", nowPlayingMovieList)

            TvListResponseWrapper("\uD83D\uDCC8 Top Rated Shows", topRatedTvList)
            TvListResponseWrapper("\uD83D\uDCE6 On the Air Shows", upcomingTvList)
            TvListResponseWrapper("\uD83D\uDE80 Popular Shows", popularTvList)
            Spacer(modifier = Modifier.size(200.dp))
        }
    }

    /**
     * Wrapper function for all the Movie lists, determines the state of the list
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

    /**
     * Wrapper function for all the TV lists, determines the state of the list
     * based on the wrapping @link{Resource}.
     */
    @Composable
    fun TvListResponseWrapper(title: String, response: Resource<TvListResponse>) {

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
                HomeScreenTvItemsRow(title, items = response.data?.results ?: listOf())
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
                        ::onItemClick
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreenTvItemsRow(title: String, items: List<TvResult>) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(20.dp),
                fontSize = 20.sp,
            )
            LazyRow {
                itemsIndexed(items = items) { _, item ->
                    HomeScreenListItem(
                        item.name,
                        item.poster_path,
                        item.id,
                        ::onTvItemClick
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreenListItem(
        title: String,
        imageUrl: String,
        mediaId: Int,
        itemClick: (mediaId: Int) -> Unit
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
                .clickable {
                    itemClick(mediaId)
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
        view?.findNavController()?.navigate(R.id.home_to_movie_detail, bundle)
    }

    private fun onTvItemClick(mediaId: Int) {
        val bundle = bundleOf("mediaId" to mediaId)
        view?.findNavController()?.navigate(R.id.home_to_tv_detail, bundle)
    }
}



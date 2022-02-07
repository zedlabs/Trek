package ml.zedlabs.tvtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.movie.MovieResult
import ml.zedlabs.tvtracker.ui.common.MovieViewModel
import ml.zedlabs.tvtracker.util.appendAsImageUrl

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
                val scrollState = rememberScrollState()
                val topRatedList by movieViewModel.topRatedMovieListState.collectAsState()
                val upcomingList by movieViewModel.upcomingMovieListState.collectAsState()
                val popularList by movieViewModel.popularMovieListState.collectAsState()
                val nowPlayingList by movieViewModel.nowPlayingMovieListState.collectAsState()

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    ListResponseWrapper("\uD83D\uDCC8 Top Rated", topRatedList)
                    ListResponseWrapper("\uD83D\uDCE6 Upcoming",upcomingList)
                    ListResponseWrapper("\uD83D\uDE80 Popular",popularList)
                    ListResponseWrapper("\uD83C\uDFA5 Now Playing",nowPlayingList)
                    Spacer(modifier = Modifier.size(200.dp))
                }
            }
        }
    }

    @Composable
    fun ListResponseWrapper(title: String, response: Resource<MovieListResponse>) {

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
                    HomeScreenListItem(item.title, item.poster_path)
                }
            }
        }
    }

    @Composable
    fun HomeScreenListItem(title: String, imageUrl: String) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
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



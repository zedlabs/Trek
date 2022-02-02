package ml.zedlabs.tvtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import ml.zedlabs.data.util.Constants
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.movie.MovieResult
import ml.zedlabs.tvtracker.ui.movie.MovieViewModel

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
           setContent {
               val scrollState = rememberScrollState()
               Column(modifier = Modifier.verticalScroll(scrollState)) {
                   ListResponseWrapper(movieViewModel.topRatedMovieListState.value)
                   ListResponseWrapper(movieViewModel.topRatedMovieListState.value)
                   ListResponseWrapper(movieViewModel.topRatedMovieListState.value)
                   ListResponseWrapper(movieViewModel.topRatedMovieListState.value)
                   ListResponseWrapper(movieViewModel.topRatedMovieListState.value)
                   Spacer(modifier = Modifier.size(200.dp))
               }

           }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        movieViewModel.getMovieList(Constants.top_rated, 1)
    }

    @Composable
    fun ListResponseWrapper(response: Resource<MovieListResponse>) {

        when(response) {
            is Resource.Error -> {
                // stop shimmer
            }
            is Resource.Loading -> {
                // start shimmer
            }
            is Resource.Success -> {
                HomeScreenItemsRow("Top Rated Movies", items = response.data?.results ?: listOf())
            }
        }
    }

    @Composable
    fun HomeScreenItemsRow(title: String, items: List<MovieResult>) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
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
        Card(modifier = Modifier
            .padding(50.dp)
            .width(200.dp)
            .height(250.dp)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter("https://www.gannett-cdn.com/presto/2020/01/10/PIND/26e99fe3-fb50-43f1-8a7d-597e6cafe8c3-GettyImages-513022286.jpg?crop=3294,2471,x463,y0&quality=50&width=640"),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(text = title, modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .border(4.dp, Color.Black)
                    .padding(20.dp))
            }

        }
    }
}



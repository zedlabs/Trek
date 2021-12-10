package ml.zedlabs.tvtracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import ml.zedlabs.data.util.Constants
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.movie.MovieResult
import ml.zedlabs.tvtracker.ui.movie.MovieViewModel

@Composable
fun HomeScreen(movieViewModel: MovieViewModel = hiltViewModel()) {
    val movieList = movieViewModel.topRatedMovieListState
    Column {
        Text(text = "Home")
        when(val response = movieViewModel.topRatedMovieListState.value) {
            is Resource.Error -> {
                //stop shimmer
            }
            is Resource.Loading -> {
                //show fake shimmer
            }
            is Resource.Success -> {
                HomeScreenItemsRow("Top Rated Movies", items = response.data?.results ?: listOf())
            }
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
        LazyColumn {
            itemsIndexed(items = items) { _, item ->
                HomeScreenListItem(item.title, item.poster_path)
            }
        }
    }
}

@Composable
fun HomeScreenListItem(title: String, imageUrl: String) {
    Card(modifier = Modifier.padding(6.dp)) {
        Box {
            Image(
                painter = rememberImagePainter("https://www.example.com/image.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            )
            Text(text = title, modifier = Modifier
                .align(Alignment.BottomCenter)
                .border(4.dp, Color.Black)
                .padding(20.dp))
        }

    }
}
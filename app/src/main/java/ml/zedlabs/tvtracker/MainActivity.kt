package ml.zedlabs.tvtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.data.util.Constants
import ml.zedlabs.domain.model.Resource.*
import ml.zedlabs.tvtracker.ui.MainScreen
import ml.zedlabs.tvtracker.ui.movie.MovieViewModel
import ml.zedlabs.tvtracker.ui.theme.TvTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel.getMovieList(Constants.top_rated, 1)
        setContent {
            TvTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun listResponseWrapper() {

        when(val response = movieViewModel.topRatedMovieListState.value) {
            is Error -> {
                // stop shimmer
            }
            is Loading -> {
                // start shimmer
            }
            is Success -> {
                // load data in list
                val items =  mutableListOf<String>()
                response.data?.results?.forEach { item ->
                    items.add(item.title)
                }
                HomeScreenItemsRow(title = "Row 1", items = items)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvTrackerTheme {
        Column(
//            modifier = Modifier
//                .background(color = Color.Cyan)
        ) {
            val data = mutableListOf<String>()
            var c = "A"
            var d = listOf(
                Color.Unspecified,
                Color.Black,
                Color.Blue,
                Color.DarkGray,
                Color.Magenta,
                Color.Yellow
            )
            for (i in 0 until 6) {
                //data.add(Pair(c, d[i]))
                c += 1
            }

            for(i in 0..3) {
                HomeScreenItemsRow(title = "Welcome", items = data)
            }
        }
    }
}

@Composable
fun HomeScreenItemsRow(title: String, items: List<String>) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
        )
        LazyColumn {
            itemsIndexed(items = items) { index, item ->
                Card(modifier = Modifier.padding(6.dp)) {
                    Text(text = item, modifier = Modifier
                        .border(4.dp, Color.Black)
                        .padding(20.dp))
                }
            }
        }
    }
}




























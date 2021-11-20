package ml.zedlabs.tvtracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.tvtracker.ui.theme.TvTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        mainViewModel.getTopRatedMovies()
        setContent {
            TvTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    fun initObserver() {
        mainViewModel.topRatedMovieListLiveData.observe(this) {
            when(it) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val t = it.data
                    t?.results?.forEach { res ->

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvTrackerTheme {
        Column(
//            modifier = Modifier
//                .background(color = Color.Cyan)
        ) {
            val data = mutableListOf<Pair<String, Color>>()
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
                data.add(Pair(c, d[i]))
                c += 1
            }

            for(i in 0..3) {
                HomeScreenItemsRow(title = "Welcome", items = data)
            }
        }
    }
}

@Composable
fun HomeScreenItemsRow(title: String, items: List<Pair<String, Color>>) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
        )
        LazyRow {
            itemsIndexed(items = items) { index, item ->
                Card() {
                    Text(text = item.first, modifier = Modifier.border(1.dp, item.second))
                }
            }
        }
    }
}




























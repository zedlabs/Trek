package ml.zedlabs.tvtracker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.tvtracker.databinding.ActivityMainBinding
import ml.zedlabs.tvtracker.ui.detail.MovieDetailsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.fragment)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movie_detail_screen, R.id.tv_detail_screen -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
//    private val movieViewModel: MovieViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        movieViewModel.getMovieList(Constants.top_rated, 1)
//        setContent {
//            TvTrackerTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//
//                }
//            }
//        }
//    }
//
//    @Composable
//    fun listResponseWrapper() {
//
//        when(val response = movieViewModel.topRatedMovieListState.value) {
//            is Error -> {
//                // stop shimmer
//            }
//            is Loading -> {
//                // start shimmer
//            }
//            is Success -> {
//                // load data in list
//                val items =  mutableListOf<String>()
//                response.data?.results?.forEach { item ->
//                    items.add(item.title)
//                }
//                //HomeScreenItemsRow(title = "Row 1", items = items)
//            }
//        }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    TvTrackerTheme {
//        Column(
////            modifier = Modifier
////                .background(color = Color.Cyan)
//        ) {
//            val data = mutableListOf<String>()
//            var c = "A"
//            var d = listOf(
//                Color.Unspecified,
//                Color.Black,
//                Color.Blue,
//                Color.DarkGray,
//                Color.Magenta,
//                Color.Yellow
//            )
//            for (i in 0 until 6) {
//                //data.add(Pair(c, d[i]))
//                c += 1
//            }
//
//            for(i in 0..3) {
//               // HomeScreenItemsRow(title = "Welcome", items = data)
//            }
//        }
//    }
    }
}






























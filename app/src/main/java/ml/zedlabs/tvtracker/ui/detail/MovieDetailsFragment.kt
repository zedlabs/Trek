package ml.zedlabs.tvtracker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.tvtracker.ui.common.MovieViewModel
import ml.zedlabs.tvtracker.ui.list.ListViewModel
import ml.zedlabs.tvtracker.util.mapToMediaCommon

/**
 *
 * @author {@zedlabs}
 * @created_on {7-02-2022}
 *
 * This is the main detail screen for all the movies,
 * contains detailed view, personalisation features for media type.
 *
 * receives data through bundle
 */
@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val detailCommonViewModel: DetailViewModel by viewModels()
    private val movieViewModel: MovieViewModel by activityViewModels()
    private val listViewModel: ListViewModel by activityViewModels()

    private var mediaId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DetailsScreenParentLayout()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mediaId = it.getInt("mediaId")
        }
        loadDataFromNetwork()
    }

    private fun loadDataFromNetwork() {
        movieViewModel.getMovieDetail(mediaId)
    }

    /**
     * main layout for the TV/Movie details screen
     * contents TBD
     */
    @Composable
    fun DetailsScreenParentLayout() {
        val movieDetails by movieViewModel.movieDetailState.collectAsState()
        val imdbRating by detailCommonViewModel.imdbRatingState.collectAsState()
        if (movieDetails is Resource.Success) {
            val movieItem = movieDetails.data?.mapToMediaCommon() ?: return
            // we want to load the imdb rating once, other it will create a ongoing loop
            LaunchedEffect(true) {
                loadImdbRating(movieDetails.data?.imdb_id ?: "")
            }
            DetailsScreenMainLayout(movieItem, imdbRating.data ?: 0.0) {
                // SAM for adding the media to users media list
                listViewModel.addToUserAddedList(
                    with(movieItem) {
                        AddedList(
                            uid = mediaId,
                            posterPath = posterPath,
                            title = title,
                            description = description,
                            type = type
                        )
                    }
                )
            }
        }
    }

    private fun loadImdbRating(id: String) {
       detailCommonViewModel.getImdbRating(id)
    }

}

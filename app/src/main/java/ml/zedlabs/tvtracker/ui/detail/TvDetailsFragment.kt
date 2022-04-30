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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.tvtracker.base.BaseAndroidFragment
import ml.zedlabs.tvtracker.ui.common.TvViewModel
import ml.zedlabs.tvtracker.ui.list.ListViewModel
import ml.zedlabs.tvtracker.ui.theme.TvTrackerTheme
import ml.zedlabs.tvtracker.util.Constants
import ml.zedlabs.tvtracker.util.mapToMediaCommon

/**
 *
 * @author {@zedlabs}
 * @created_on {9-02-2022}
 *
 * This is the main detail screen for all the TV shows,
 * contains detailed view, personalisation features for media type.
 *
 * receives data through bundle
 */
@AndroidEntryPoint
class TvDetailsFragment : BaseAndroidFragment() {

    private val tvViewModel: TvViewModel by activityViewModels()
    private val detailCommonViewModel: DetailViewModel by viewModels()
    private val listViewModel: ListViewModel by activityViewModels()
    private var mediaId = 0

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
                    DetailsScreenParentLayout()
                }
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
        tvViewModel.getTvShowDetails(mediaId)
        tvViewModel.getTvExternalIds(mediaId)
    }

    /**
     * main layout for the TV/Movie details screen
     * contents TBD
     */
    @Composable
    fun DetailsScreenParentLayout() {
        val tvDetails by tvViewModel.tvDetailState.collectAsState()
        val externalId by tvViewModel.tvExternalIdsState.collectAsState()
        val imdbRating by detailCommonViewModel.imdbRatingState.collectAsState()
        if (externalId is Resource.Success) {
            LaunchedEffect(true) {
                loadImdbRating(externalId.data?.imdb_id ?: "")
            }
        }

        if (tvDetails is Resource.Success) {
            val tvItem = tvDetails.data?.mapToMediaCommon() ?: return
            // rating currently 0 will change once getExternal Ids have been implemented
            DetailsScreenMainLayout(tvItem, imdbRating.data ?: 0.0) {
                // SAM for adding the media to users media list
                listViewModel.addToUserAddedList(
                    with(tvItem) {
                        AddedList(
                            uid = mediaId,
                            posterPath = posterPath,
                            title = title,
                            description = description,
                            type = type,
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
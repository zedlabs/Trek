package ml.zedlabs.tvtracker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.tvtracker.ui.common.TvViewModel
import ml.zedlabs.tvtracker.util.appendAsImageUrl

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
class TvDetailsFragment: Fragment() {

    private val tvViewModel: TvViewModel by activityViewModels()
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
        tvViewModel.getTvShowDetails(mediaId)
    }

    /**
     * main layout for the TV/Movie details screen
     * contents TBD
     */
    @Composable
    fun DetailsScreenParentLayout() {
        val scrollState = rememberScrollState()
        val tvDetails by tvViewModel.tvDetailState.collectAsState()

        if(tvDetails is Resource.Success) {
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                TopInfoCard(tvDetails.data)
                Spacer(modifier = Modifier.size(200.dp))
            }
        }

    }

    @Composable
    fun TopInfoCard(tvDetails: TvDetailResponse?) {
        tvDetails ?: return
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(tvDetails.poster_path?.appendAsImageUrl()),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(
                    color = Color.White,
                    text = tvDetails.name ?: "",
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
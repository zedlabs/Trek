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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import ml.zedlabs.tvtracker.util.appendAsImageUrl

/**
 *
 * @author {@zedlabs}
 * @created_on {7-02-2022}
 *
 * This is the main detail screen for all the movies/TV shows,
 * contains detailed view, personalisation features for media type.
 *
 * receives data through nav args
 */
class DetailsFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()

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

    /**
     * main layout for the TV/Movie details screen
     * contents TBD
     */
    @Composable
    fun DetailsScreenParentLayout() {
        val scrollState = rememberScrollState()

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            TopInfoCard()
            Spacer(modifier = Modifier.size(200.dp))
        }
    }

    @Composable
    fun TopInfoCard() {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter("image - url"),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(
                    color = Color.White,
                    text = "Title",
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
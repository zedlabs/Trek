package ml.zedlabs.tvtracker.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
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
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.model.common.MediaType
import ml.zedlabs.tvtracker.R
import ml.zedlabs.tvtracker.util.appendAsImageUrl
import kotlin.random.Random

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ListScreenParentLayout()
            }
        }
    }

    @Composable
    fun ListScreenParentLayout() {
        val userItems by listViewModel.userAddedListState.collectAsState(initial = listOf())
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row (
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally),
                    ){
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        listViewModel.addToUserAddedList(
                            item = AddedList(
                                uid = (0..10000).random(),
                                title = "sample title",
                                description = "sample description",
                                posterPath = "broken",
                                type = MediaType.TV,
                            )
                        )
                    }) {
                    Text(text = "Add Item")
                }
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        listViewModel.deleteAllEntries()
                    }) {
                    Text(text = "Clear Db")
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = userItems) { item ->
                    ListItem(
                        title = item.title ?: "",
                        imageUrl = item.posterPath ?: "",
                        mediaId = item.uid,
                        type = item.type,
                    )
                }
            }
        }
    }

    @Composable
    fun ListItem(
        title: String,
        imageUrl: String,
        mediaId: Int,
        type: MediaType,
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(163.dp)
                .height(245.dp)
                .clickable {
                   onItemClick(mediaId = mediaId, mediaType = type)
                }
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
    private fun onItemClick(mediaId: Int, mediaType: MediaType) {
        val bundle = bundleOf("mediaId" to mediaId)
        with(view?.findNavController()) {
            // exit out if the nav controller instance cannot be found
            this?: return
            when(mediaType) {
                MediaType.MOVIE -> navigate(R.id.list_to_movie_details, bundle)
                MediaType.TV , MediaType.ANIME-> navigate(R.id.list_to_tv_detail, bundle)

            }
        }
    }

}
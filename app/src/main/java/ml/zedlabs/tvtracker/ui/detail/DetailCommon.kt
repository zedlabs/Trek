package ml.zedlabs.tvtracker.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ml.zedlabs.domain.model.common.MediaDetailCommon
import ml.zedlabs.tvtracker.util.appendAsImageUrl

@Composable
fun DetailsScreenMainLayout(
    itemDetails: MediaDetailCommon?,
    addToDb: () -> Unit,
) {
    itemDetails ?: return
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        ) {
            Image(
                painter = rememberImagePainter(itemDetails.posterPath?.appendAsImageUrl()),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                color = Color.White,
                text = itemDetails.title ?: "",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            )
                        )
                    )
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 32.sp
            )
        }
        Button(
            onClick = {
                addToDb.invoke()
            }
        ) {
            Text(text = "Add to Your List")
        }
        Text(
            text = "Vote Count: : ${itemDetails.formattedVoteCount}"
        )
        Text(
            text = "🌟 Ratings: ${itemDetails.formattedRating}"
        )
        Text(
            text = itemDetails.description ?: ""
        )
    }
}


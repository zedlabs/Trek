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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ml.zedlabs.domain.model.common.MediaDetailCommon
import ml.zedlabs.tvtracker.ui.theme.Typography
import ml.zedlabs.tvtracker.util.appendAsImageUrl
import ml.zedlabs.tvtracker.util.asRating

@Composable
fun DetailsScreenMainLayout(
    itemDetails: MediaDetailCommon?,
    imdbRating: Double,
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
                    .padding(15.dp),
                style = Typography.h1,
            )
        }
        Button(
            onClick = {
                addToDb.invoke()
            }
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                Text(text = "Add to Your List")
            }
        }
        Text(
            text = "Vote Count: : ${itemDetails.formattedVoteCount}",
            style = Typography.body1,
        )
        Text(
            text = "Ratings: ${itemDetails.formattedRating}",
            style = Typography.body1,
            fontSize = 30.sp
        )
        if (imdbRating != 0.0) {
            Text(
                text = "Imdb Ratings: ${imdbRating.asRating()}",
                style = Typography.body1,
            )
        }
        Text(
            text = itemDetails.description ?: "",
            style = Typography.body1,
        )
    }
}


package dev.bogibek.tvshowdecrative.activity.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import dev.bogibek.tvshowdecrative.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    show_id: String,
    show_name: String,
    show_type: String
) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    viewModel.apiTVShowDetails(show_id.toInt())
    DetailsScreenContent(viewModel, show_id = show_id, show_name = show_name, show_type = show_type)
}

@Composable
fun DetailsScreenContent(
    viewModel: DetailsViewModel,
    show_id: String,
    show_name: String,
    show_type: String
) {
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val tvShowDetails by viewModel.tvShowDetails.observeAsState()


    Scaffold(backgroundColor = Color.Black) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),

                ) {

            }
        } else {
            Column {
                Box {
                    Box(modifier = Modifier.height(500.dp)) {
                        val imgUrl =
                            "https://static.episodate.com/images/tv-show/full/${show_id}.jpg"
                        GlideImage(
                            imageModel = imgUrl,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Box(modifier = Modifier.height(500.dp)) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .background(Color.Black.copy(alpha = 0.6f)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(15.dp))
                                Column {
                                    Text(text = show_name, color = Color.White)
                                    Text(text = show_type, color = Color.Yellow)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
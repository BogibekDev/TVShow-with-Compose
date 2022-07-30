package dev.bogibek.tvshowdecrative.activity.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.bogibek.tvshowdecrative.item.itemTVShow
import dev.bogibek.tvshowdecrative.model.TVShow
import dev.bogibek.tvshowdecrative.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val tvShows = viewModel.movie
    MainScreenContent(isLoading, tvShows) {
        //save TVShow to local
        viewModel.insertTVShowToDB(it)
        //open details Screen
        navController.navigate("details/${it.id}/${it.name}/${it.network}")
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(
    isLoading: Boolean,
    tvShows: Flow<PagingData<TVShow>>,
    onItemClick: ((TVShow) -> Unit)?
) {
    Scaffold(
        backgroundColor = Color.Black,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = { Text(text = "TV Show", color = Color.White) },
            )
        }
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
        } else {
            val tvShowList: LazyPagingItems<TVShow> = tvShows.collectAsLazyPagingItems()
            LazyVerticalGrid(
                GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp)
            ) {

                items(tvShowList.itemCount) {
                    val tvShow = tvShowList[it]
                    itemTVShow(tvShow!!, onItemClick)
                }
            }
        }
    }
}



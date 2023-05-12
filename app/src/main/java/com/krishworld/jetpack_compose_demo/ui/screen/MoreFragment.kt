package com.krishworld.jetpack_compose_demo.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.krishworld.jetpack_compose_demo.components.NetworkImageBanner
import com.krishworld.jetpack_compose_demo.components.ShimmerEffect
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import com.krishworld.jetpack_compose_demo.viewmodel.MainViewModel


@Composable
fun MoreFragment(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        MoreScreen(navController)
    }
}

@Composable
fun MoreScreen(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(photos) { photo ->
            Box {
                photo?.downloadUrl?.let {
                    NetworkImageBanner(
                        url = it
                    )
                }
                BasicText(
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    text = "Clicked by: " + photo?.author.toString(),
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp)
                        .clip(RoundedCornerShape(topStartPercent = 62))
                        .background(color = Purple40)
                        .padding(8.dp)
                )
            }
        }

        photos.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    //  ProgressIndicator()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ShimmerEffect()
                        ShimmerEffect()
                        ShimmerEffect()
                        ShimmerEffect()
                        ShimmerEffect()
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        //  ProgressIndicator()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ShimmerEffect()
                            ShimmerEffect()
                            ShimmerEffect()
                            ShimmerEffect()
                            ShimmerEffect()
                        }
                    }
                }
            }
        }

    }
}


package com.krishworld.jetpack_compose_demo.ui.screen.dashboard.more


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.components.NetworkImageBanner
import com.krishworld.jetpack_compose_demo.components.ProgressSimmerList
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import com.krishworld.jetpack_compose_demo.viewmodel.dashboard.more.MoreViewModel


@Composable
fun MoreFragment(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        MoreScreen(navController)
    }
}

@Composable
fun MoreScreen(navController: NavHostController) {
    val viewModel: MoreViewModel = hiltViewModel()
    val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(
            count = photos.itemCount,
            key = photos.itemKey(),
            contentType = photos.itemContentType(
            )
        ) { index ->
            val item = photos[index]
            Box {
                item?.downloadUrl?.let {
                    NetworkImageBanner(
                        url = it
                    )
                }
                BasicText(
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    text = stringResource(R.string.clicked_by, item?.author.toString()),
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.s_vertical_spacing), start = dimensionResource(R.dimen.s_horizontal_spacing))
                        .clip(RoundedCornerShape(topStartPercent = 62))
                        .background(color = Purple40)
                        .padding(dimensionResource(R.dimen.xs_surrounding_spacing))
                )
            }
        }

        photos.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    ProgressSimmerList()
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        ProgressSimmerList()
                    }
                }
            }
        }

    }
}


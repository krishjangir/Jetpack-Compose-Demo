package com.krishworld.jetpack_compose_demo.ui.screen.dashboard.home

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.components.MessageRow
import com.krishworld.jetpack_compose_demo.components.VideoPlayer
import com.krishworld.jetpack_compose_demo.components.ViewPager

@UnstableApi
@Composable
fun HomeScreen(navController: NavHostController) {

    val videoURL =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"


    LazyColumn(
    ) {
        item { VideoPlayer(Uri.parse(videoURL)) }

        //-----------ViewPager----------------
        item { ViewPager() }
        //-----------MessageList----------------
        items(5) { index ->
            MessageRow(message = stringResource(R.string.message, index))
        }
        //-----------Bottom Margin----------------
        item {
            Spacer(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.xxl_vertical_spacing)))
        }
    }
}


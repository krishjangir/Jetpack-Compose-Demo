package com.krishworld.jetpack_compose_demo.ui.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
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
            MessageRow(message = "message $index")
        }
        //-----------Bottom Margin----------------
        item {
            Spacer(modifier = Modifier.padding(bottom = 64.dp))
        }
    }
}


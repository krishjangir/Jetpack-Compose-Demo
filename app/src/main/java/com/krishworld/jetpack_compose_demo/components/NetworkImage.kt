package com.krishworld.jetpack_compose_demo.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.krishworld.jetpack_compose_demo.ui.theme.Purple80


@Composable
fun NetworkImage(url: String) {
    Image(
        painter = rememberGlidePainter(url),
        modifier = Modifier
            .padding(14.dp)
            .size(60.dp)
            .clip(CircleShape) // clip to the circle shape
            .border(5.dp, Color.White, CircleShape)
            .padding(14.dp),
        contentDescription = "",
    )
}

@Composable
fun NetworkImageBanner(url: String) {
    Image(
        painter = rememberGlidePainter(url),
        modifier = Modifier
            .height(250.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Color.Gray, RoundedCornerShape(10))
            .background(Purple80)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
        contentDescription = "",
    )
}


@Composable
fun NetworkImageThumbnail(url: String) {
    Image(
        painter = rememberGlidePainter(url),
        modifier = Modifier
            .height(230.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(5))
            .border(4.dp, Color.Gray, RoundedCornerShape(5))
            .background(Purple80)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
        contentDescription = "",
    )
}
package com.krishworld.jetpack_compose_demo.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import com.krishworld.jetpack_compose_demo.ui.theme.Purple80

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPager() {
    val slideImage = remember { mutableStateOf(R.drawable.ic_notification) }
    val pagerState: PagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .height(230.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(count = 3, state = pagerState) { page ->
            when (page) {
                0 -> {
                    slideImage.value = R.drawable.ic_notification
                }

                1 -> {
                    slideImage.value = R.drawable.ic_mic
                }

                2 -> {
                    slideImage.value = R.drawable.ic_music
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(slideImage.value),
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .height(200.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10))
                        .border(2.dp, Color.Gray, RoundedCornerShape(10))
                        .background(Purple80)
                        .fillMaxWidth(),
                    contentDescription = ""
                )

            }
        }
        Spacer(modifier = Modifier.padding(4.dp))

        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            selectedColor = Purple40,
            unSelectedColor = Purple80
        )
    }
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}
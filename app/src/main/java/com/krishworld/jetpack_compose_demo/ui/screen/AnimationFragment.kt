package com.krishworld.jetpack_compose_demo.ui.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.krishworld.jetpack_compose_demo.R

@Composable
fun AnimationFragment(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimationScreen(navController)
    }
}

@Composable
fun AnimationScreen(navController: NavHostController) {
    var bikeState = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState2 = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState3 = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState4 = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState5 = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState6 = rememberSaveable { mutableStateOf(ItemPosition.Start) }
    var bikeState7 = rememberSaveable { mutableStateOf(ItemPosition.Start) }


    val offsetAnimation: Dp by animateDpAsState(
        if (bikeState.value == ItemPosition.Start) 5.dp else 200.dp,
        tween(4000, easing = CubicBezierEasing(0.4f, 0.0f, 0.8f, 0.8f))
    )

    val offsetAnimation2: Dp by animateDpAsState(
        if (bikeState2.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(dampingRatio = Spring.DampingRatioLowBouncy)
    )

    val offsetAnimation3: Dp by animateDpAsState(
        if (bikeState3.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    val offsetAnimation4: Dp by animateDpAsState(
        if (bikeState4.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )

    val offsetAnimation5: Dp by animateDpAsState(
        if (bikeState5.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(stiffness = Spring.StiffnessLow)
    )

    val offsetAnimation6: Dp by animateDpAsState(
        if (bikeState6.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(stiffness = Spring.StiffnessMedium)
    )

    val offsetAnimation7: Dp by animateDpAsState(
        if (bikeState7.value == ItemPosition.Start) 5.dp else 200.dp,
        spring(stiffness = Spring.StiffnessHigh)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        item { BikeItem(bikeState, offsetAnimation) }
        item { BikeItem(bikeState2, offsetAnimation2) }
        item { BikeItem(bikeState3, offsetAnimation3) }
        item { BikeItem(bikeState4, offsetAnimation4) }
        item { BikeItem(bikeState5, offsetAnimation5) }
        item { BikeItem(bikeState6, offsetAnimation6) }
        item { BikeItem(bikeState7, offsetAnimation7) }
    }
}

@Composable
fun BikeItem(itemPosition: MutableState<ItemPosition>, offsetAnimation: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.img_cycle),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .absoluteOffset(x = offsetAnimation)
        )
        Button(
            onClick = {
                itemPosition.value = when (itemPosition.value) {
                    ItemPosition.Start -> ItemPosition.Finish
                    ItemPosition.Finish -> ItemPosition.Start
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Start Anim bikeState7")
        }
    }
}

enum class ItemPosition {
    Start, Finish
}
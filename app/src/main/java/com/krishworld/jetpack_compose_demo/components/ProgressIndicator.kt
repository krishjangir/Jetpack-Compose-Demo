package com.krishworld.jetpack_compose_demo.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun ProgressIndicatorPreview() {
    ProgressIndicator()
}

@Composable
fun ProgressSimmerList() {
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

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun ProgressSimmerListPreview() {
    ProgressSimmerList()
}


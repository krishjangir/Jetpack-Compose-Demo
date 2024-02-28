package com.krishworld.jetpack_compose_demo.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomAppBarView(selectedIndex: MutableState<Int>) {
    val result = remember { mutableStateOf("") }
    val selectedItem = remember { mutableStateOf("home") }

    BottomAppBar(
        content = {
            NavigationBar() {
                NavigationBarItem(
                    icon = {
                        Icon(Icons.Filled.Home, "")
                    },
                    label = { Text(text = "Home") },
                    selected = selectedItem.value == "home",
                    onClick = {
                        result.value = "Home icon clicked"
                        selectedItem.value = "home"
                        selectedIndex.value = 0
                    },
                    alwaysShowLabel = false
                )

                NavigationBarItem(
                    icon = {
                        Icon(Icons.Filled.Star, "")
                    },
                    label = { Text(text = "Animation") },
                    selected = selectedItem.value == "animation",
                    onClick = {
                        result.value = "Animation icon clicked"
                        selectedItem.value = "animation"
                        selectedIndex.value = 1
                    },
                    alwaysShowLabel = false
                )

                NavigationBarItem(
                    icon = {
                        Icon(Icons.Filled.AccountCircle, "")
                    },
                    label = { Text(text = "Profile") },
                    selected = selectedItem.value == "profile",
                    onClick = {
                        result.value = "Profile icon clicked"
                        selectedItem.value = "profile"
                        selectedIndex.value = 2
                    },
                    alwaysShowLabel = false
                )

                NavigationBarItem(
                    icon = {
                        Icon(Icons.Filled.Menu, "")
                    },
                    label = { Text(text = "More") },
                    selected = selectedItem.value == "more",
                    onClick = {
                        result.value = "More icon clicked"
                        selectedItem.value = "more"
                        selectedIndex.value = 3
                    },
                    alwaysShowLabel = false
                )
            }
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun BottomAppBarViewPreview() {
    BottomAppBarView(mutableStateOf(0))
}

